package com.francis.weatherap.domain.city

import com.francis.weatherap.data.interactors.city.InsertFavoriteCityInteractor
import com.francis.weatherap.data.repository.favorite.FavoriteRepository
import javax.inject.Inject

class InsertFavoriteCityInteractorImpl @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
) : InsertFavoriteCityInteractor {

    override suspend fun invoke(params: InsertFavoriteCityInteractor.Param) = favoriteRepository
        .saveFavoriteCity(params.city.toRepoFavoriteCity())
}
