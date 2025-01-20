package com.francis.weatherap.data.interactors.city

import com.francis.weatherap.data.interactors.city.model.FavoriteCity
import com.francis.weatherap.core.foundation.StreamInteractor

/** Loads the favorite cities */
abstract class GetFavoriteCitiesInteractor : StreamInteractor<Unit, List<FavoriteCity>>()
