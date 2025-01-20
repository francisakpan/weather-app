package com.francis.weatherap.data.repository.weather.dif.model.today

import com.francis.weatherap.data.repository.weather.dif.model.Current
import com.francis.weatherap.data.repository.weather.dif.model.Location

/**
 * Current weather conditions
 *
 * @property current weather conditions
 * @property location weather location
 */
data class TodayWeatherResponse(
    val current: Current,
    val location: Location,
)
