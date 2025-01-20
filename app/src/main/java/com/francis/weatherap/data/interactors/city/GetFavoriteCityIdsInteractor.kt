package com.francis.weatherap.data.interactors.city

import com.francis.weatherap.core.foundation.StreamInteractor

abstract class GetFavoriteCityIdsInteractor : StreamInteractor<Unit, Set<Long>>()
