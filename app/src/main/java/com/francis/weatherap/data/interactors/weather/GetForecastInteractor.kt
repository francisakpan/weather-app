package com.francis.weatherap.data.interactors.weather

import com.francis.weatherap.core.foundation.Interactor
import com.francis.weatherap.core.types.either.Either
import com.francis.weatherap.data.interactors.weather.model.Forecast

/** Gets the forecast for the [WeatherLocation] */
interface GetForecastInteractor : Interactor<GetForecastInteractor.Params, Either<Forecast>> {

    /**
     * [GetForecastInteractor] configuration parameters
     *
     * @property location [WeatherLocation] for the weather forecast
     */
    data class Params(val location: WeatherLocation)
}
