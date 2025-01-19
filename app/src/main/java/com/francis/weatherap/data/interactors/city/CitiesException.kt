package com.francis.weatherap.data.interactors.city

import java.io.IOException

/** Exception thrown when loading cities */
class CitiesException(
    message: String? = null,
    cause: Throwable? = null,
) : IOException(message, cause)
