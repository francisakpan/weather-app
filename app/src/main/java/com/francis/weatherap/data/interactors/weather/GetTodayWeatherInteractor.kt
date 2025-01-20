package com.francis.weatherap.data.interactors.weather

import com.francis.weatherap.core.foundation.Interactor
import com.francis.weatherap.core.types.either.Either
import com.francis.weatherap.data.interactors.weather.model.TodayWeather

/** Gets the current weather */
interface GetTodayWeatherInteractor :
    Interactor<GetTodayWeatherInteractor.Params, Either<TodayWeather>> {

    /**
     * Configuration parameters for the [GetTodayWeatherInteractor]
     *
     * @property location the [WeatherLocation] for the current weather
     */
    data class Params(val location: WeatherLocation)
}
