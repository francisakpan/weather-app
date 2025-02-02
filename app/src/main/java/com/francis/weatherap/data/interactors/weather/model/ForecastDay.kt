package com.francis.weatherap.data.interactors.weather.model

import java.time.ZonedDateTime

/**
 * Weather forecast for a day
 *
 * @property date - the date of each forecast
 * @property sunrise - the sunrise time
 * @property sunset - the sunset time
 * @property entries - a [List] of [ForecastEntry] for this day
 */
data class ForecastDay(
    val date: ZonedDateTime,
    val sunrise: String,
    val sunset: String,
    val entries: List<ForecastEntry>,
)
