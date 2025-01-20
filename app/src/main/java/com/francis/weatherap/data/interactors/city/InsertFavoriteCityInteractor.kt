package com.francis.weatherap.data.interactors.city

import com.francis.weatherap.data.interactors.city.model.FavoriteCity
import com.francis.weatherap.core.foundation.Interactor

/** Stores a [RecentCity] in persistent memory */
interface InsertFavoriteCityInteractor : Interactor<InsertFavoriteCityInteractor.Param, Unit> {

    /**
     * Configuration parameters for [InsertFavoriteCityInteractor]
     *
     * @property city the [FavoriteCity] to store
     */
    data class Param(val city: FavoriteCity)
}
