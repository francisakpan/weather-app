package com.francis.weatherap.domain.city

import com.francis.weatherap.data.interactors.city.DeleteFavoriteCityInteractor
import com.francis.weatherap.data.repository.favorite.FavoriteRepository
import javax.inject.Inject

class DeleteFavoriteCityInteractorImpl @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
) : DeleteFavoriteCityInteractor {

    override suspend fun invoke(params: DeleteFavoriteCityInteractor.Params) {
        favoriteRepository.deleteFavoriteCity(params.cityId)
    }
}
