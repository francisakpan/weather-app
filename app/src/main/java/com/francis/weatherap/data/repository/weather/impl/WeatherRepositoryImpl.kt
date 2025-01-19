package com.francis.weatherap.data.repository.weather.impl

import com.francis.weatherap.core.types.either.Either
import com.francis.weatherap.core.types.either.fold
import com.francis.weatherap.core.util.safeApiCall
import com.francis.weatherap.data.repository.weather.dif.WeatherException
import com.francis.weatherap.data.repository.weather.dif.WeatherLocation
import com.francis.weatherap.data.repository.weather.dif.WeatherRepository
import com.francis.weatherap.data.repository.weather.dif.model.forecast.ForecastResponse
import com.francis.weatherap.data.repository.weather.dif.model.today.TodayWeatherResponse
import com.francis.weatherap.di.dispatchers.DispatcherProvider
import com.francis.weatherap.framework.network.weather.WeatherService
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService,
    private val dispatcherProvider: DispatcherProvider,
) : WeatherRepository {

    override suspend fun getTodayWeather(
        location: WeatherLocation,
    ): Either<TodayWeatherResponse> {
        val response = safeApiCall {
            weatherService.getTodayWeather(
                query = location.formattedQuery,
            )
        }
        return response.fold(
            onSuccess = { model ->
                if (model.isValid) {
                    withContext(dispatcherProvider.default) {
                        Either.Success(model.toWeatherResponse())
                    }
                } else {
                    Either.Failure(
                        WeatherException(
                            message = "Failed to load today weather",
                        ),
                    )
                }
            },
            onFailure = { throwable ->
                Either.Failure(
                    WeatherException(
                        message = throwable.message ?: "Failed to load today weather",
                        cause = throwable,
                    ),
                )
            },
        )
    }

    override suspend fun getForecast(
        location: WeatherLocation,
        days: Int,
    ): Either<ForecastResponse> {
        val response = safeApiCall {
            weatherService.getForecast(
                query = location.formattedQuery,
                days = days,
            )
        }
        return response.fold(
            onSuccess = { model ->
                if (model.isValid) {
                    withContext(dispatcherProvider.default) {
                        Either.Success(model.toForecastResponse())
                    }
                } else {
                    Either.Failure(
                        WeatherException(
                            message = "Failed to load weather forecast",
                        ),
                    )
                }
            },
            onFailure = { throwable ->
                Either.Failure(
                    WeatherException(
                        message = throwable.message ?: "Failed to load weather forecast",
                        cause = throwable,
                    ),
                )
            },
        )
    }

    private val WeatherLocation.formattedQuery: String
        get() = when (this) {
            is WeatherLocation.City -> "$name,$countryCode"
            is WeatherLocation.Location -> coordinates.asIso6709
        }
}
