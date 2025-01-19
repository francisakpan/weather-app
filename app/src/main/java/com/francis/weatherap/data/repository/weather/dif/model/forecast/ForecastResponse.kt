package com.francis.weatherap.data.repository.weather.dif.model.forecast

import com.francis.weatherap.data.repository.weather.dif.model.Current
import com.francis.weatherap.data.repository.weather.dif.model.Location

/**
 * Weather forecast
 *
 * @property current current weather conditions
 * @property location location for the forecast
 * @property forecast weather forecast
 */
data class ForecastResponse(
    val current: Current,
    val location: Location,
    val forecast: Forecast,
)
