package com.francis.weatherap.data.interactors.weather.model

import com.francis.weatherap.core.types.weather.AverageVisibility
import com.francis.weatherap.core.types.weather.Humidity
import com.francis.weatherap.core.types.weather.Precipitation
import com.francis.weatherap.core.types.weather.Pressure
import com.francis.weatherap.core.types.weather.Speed
import com.francis.weatherap.core.types.weather.Temperature
import com.francis.weatherap.core.types.weather.UvIndex

/**
 * Current weather conditions
 *
 * @property description the current weather description
 * @property code the code for the weather icon
 * @property temperature the temperature in Celsius
 * @property feelsLike the feels like temperature in Celsius
 * @property wind the wind speed
 * @property gust the gusts speed
 * @property humidity the humidity percentage
 * @property pressure the atmospheric pressure in millibars
 * @property precipitation the precipitation in mm
 * @property uvIndex the UV index
 * @property visibility the average visibility
 * @property iconCode the icon ID for the weather conditions
 */
data class Current(
    val description: String,
    val code: Int,
    val temperature: Temperature,
    val feelsLike: Temperature,
    val wind: Speed,
    val gust: Speed,
    val humidity: Humidity,
    val pressure: Pressure,
    val precipitation: Precipitation,
    val uvIndex: UvIndex,
    val visibility: AverageVisibility,
    val iconCode: Int,
)
