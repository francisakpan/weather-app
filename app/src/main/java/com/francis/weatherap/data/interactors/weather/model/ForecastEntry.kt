package com.francis.weatherap.data.interactors.weather.model

import com.francis.weatherap.core.types.weather.Precipitation
import com.francis.weatherap.core.types.weather.Temperature
import com.francis.weatherap.core.types.weather.Speed
import com.francis.weatherap.core.types.weather.UvIndex
import com.francis.weatherap.core.types.weather.Humidity
import com.francis.weatherap.core.types.weather.AverageVisibility
import java.time.ZonedDateTime

/**
 * A weather forecast
 *
 * @property zonedDateTime - the [ZonedDateTime] this forecast is for
 * @property description - forecast description
 * @property iconCode - the code for the icon representing this forecast
 * @property temperature - the temperature
 * @property feelsLike - the feels like temperature
 * @property precipitation - the amount of precipitation
 * @property windSpeed - the wind speed
 * @property uvIndex - the UV index
 * @property humidity - the humidity percent
 * @property visibility - the visibility in meters
 */
data class ForecastEntry(
    val zonedDateTime: ZonedDateTime,
    val description: String,
    val iconCode: Int,
    val temperature: Temperature,
    val feelsLike: Temperature,
    val precipitation: Precipitation,
    val windSpeed: Speed,
    val uvIndex: UvIndex,
    val humidity: Humidity,
    val visibility: AverageVisibility,
)
