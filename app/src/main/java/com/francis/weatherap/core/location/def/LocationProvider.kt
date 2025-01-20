package com.francis.weatherap.core.location.def

import com.francis.weatherap.core.types.location.Coordinates

interface LocationProvider {
    suspend fun getCurrentLocation(): Coordinates?
}
