package com.francis.weatherap.core.time.def

import java.time.ZoneId

/** Provides the local [ZoneId] for time conversions */
interface ZoneIdProvider {
    /** The local [ZoneId] */
    val zoneId: ZoneId
}
