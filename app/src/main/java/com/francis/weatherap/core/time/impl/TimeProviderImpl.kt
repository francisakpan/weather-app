package com.francis.weatherap.core.time.impl

import com.francis.weatherap.core.time.def.TimeProvider
import java.time.Instant
import javax.inject.Inject

class TimeProviderImpl @Inject constructor() : TimeProvider {
    override val epoch: Instant
        get() = Instant.now()
}
