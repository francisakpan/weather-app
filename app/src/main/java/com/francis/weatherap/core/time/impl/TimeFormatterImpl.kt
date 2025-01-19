package com.francis.weatherap.core.time.impl

import com.francis.weatherap.core.time.def.TimeFormatter
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

private const val DayWithDayOfWeekFormat = "EEE MMMM, dd"
private const val HourFormat = "HH:mm"


class TimeFormatterImpl @Inject constructor() : TimeFormatter {
    override fun formatDayWithDayOfWeek(zonedDateTime: ZonedDateTime): String {
        val formatter = DateTimeFormatter.ofPattern(DayWithDayOfWeekFormat)
        return formatter.format(zonedDateTime)
    }

    override fun formatHour(zonedDateTime: ZonedDateTime): String {
        val formatter = DateTimeFormatter.ofPattern(HourFormat)
        return formatter.format(zonedDateTime)
    }
}
