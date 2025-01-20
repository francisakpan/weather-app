package com.francis.weatherap.data.interactors.weather.model

import com.francis.weatherap.core.types.weather.Humidity
import com.francis.weatherap.core.types.weather.Precipitation
import com.francis.weatherap.core.types.weather.Pressure
import com.francis.weatherap.core.types.weather.Temperature
import com.francis.weatherap.core.types.weather.UvIndex

/**
 * Main weather attributes
 *
 * @property description - precipitation in mm
 * @property code - weather code
 * @property temperature - temperature
 * @property feelsLike - feels like temperature
 * @property humidity - humidity percent
 * @property pressure - atmospheric pressure
 * @property precipitation - precipitation in mm
 * @property uvIndex - UV index
 */
data class TodayMain(
    val description: String,
    val code: Int,
    val temperature: Temperature,
    val feelsLike: Temperature,
    val humidity: Humidity,
    val pressure: Pressure,
    val precipitation: Precipitation,
    val uvIndex: UvIndex,
)
