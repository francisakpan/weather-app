package com.francis.weatherap.data.interactors.weather.model

import com.francis.weatherap.core.types.weather.AverageVisibility

/**
 * Weather for today
 *
 * @property main - main weather attributes
 * @property wind - wind speed
 * @property visibility - visibility in meters
 * @property clouds - cloud cover
 */
data class TodayWeather(
    val main: TodayMain,
    val wind: TodayWind,
    val visibility: AverageVisibility,
    val clouds: TodayClouds,
)
