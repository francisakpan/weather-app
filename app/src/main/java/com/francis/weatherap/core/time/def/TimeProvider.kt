package com.francis.weatherap.core.time.def

import java.time.Instant

/** Provides the current system time */
interface TimeProvider {
    /** Elapsed time since the Unix epoch */
    val epoch: Instant
}
