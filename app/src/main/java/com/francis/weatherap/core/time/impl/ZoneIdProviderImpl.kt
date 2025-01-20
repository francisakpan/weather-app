package com.francis.weatherap.core.time.impl

import com.francis.weatherap.core.time.def.ZoneIdProvider
import java.time.ZoneId
import javax.inject.Inject

class ZoneIdProviderImpl @Inject constructor() : ZoneIdProvider {
    override val zoneId: ZoneId
        get() = ZoneId.systemDefault()
}
