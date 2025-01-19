package com.francis.weatherap.domain.weather

import com.francis.weatherap.core.types.either.Either
import com.francis.weatherap.core.types.either.fold
import com.francis.weatherap.core.types.weather.AverageVisibility
import com.francis.weatherap.data.interactors.weather.GetTodayWeatherInteractor
import com.francis.weatherap.data.interactors.weather.WeatherException
import com.francis.weatherap.data.interactors.weather.model.TodayWeather
import com.francis.weatherap.data.repository.weather.dif.WeatherRepository
import com.francis.weatherap.di.dispatchers.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetTodayWeatherInteractorImpl @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val dispatcherProvider: DispatcherProvider,
) : GetTodayWeatherInteractor {

    override suspend fun invoke(params: GetTodayWeatherInteractor.Params): Either<TodayWeather> {
        val response = weatherRepository.getTodayWeather(params.location.toRepositoryLocation())
        return response.fold(
            onSuccess = { weatherResponse ->
                withContext(dispatcherProvider.default) {
                    val weather = TodayWeather(
                        main = weatherResponse.toTodayMain(),
                        wind = weatherResponse.toTodayWind(),
                        visibility = AverageVisibility.fromKm(weatherResponse.current.visibilityKm),
                        clouds = weatherResponse.toTodayClouds(),
                    )
                    Either.Success(weather)
                }
            },
            onFailure = { throwable ->
                Either.Failure(
                    WeatherException(
                        throwable.message ?: "Error fetching today weather",
                        throwable,
                    ),
                )
            },
        )
    }
}
