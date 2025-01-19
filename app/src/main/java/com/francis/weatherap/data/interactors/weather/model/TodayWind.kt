package com.francis.weatherap.data.interactors.weather.model

import com.francis.weatherap.core.types.weather.Speed


/**
 * Wind
 *
 * @property direction - wind direction
 * @property speed - wind speed
 * @property gust - gust speed
 */
data class TodayWind(
    val direction: String,
    val speed: Speed,
    val gust: Speed,
)
