package com.francis.weatherap.data.interactors.weather

import java.io.IOException

/** Exception thrown when fetching weather data */
class WeatherException @JvmOverloads constructor(
    message: String? = null,
    cause: Throwable? = null,
) : IOException(message, cause)
