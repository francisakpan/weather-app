package com.francis.weatherap.domain.city

import com.francis.weatherap.data.interactors.city.GetFavoriteCityIdsInteractor
import com.francis.weatherap.data.repository.favorite.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavoriteCityIdsInteractorImpl @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
) : GetFavoriteCityIdsInteractor() {

    override fun buildStream(params: Unit): Flow<Set<Long>> = favoriteRepository
        .getFavoriteCities()
        .map { cities ->
            cities.map { city ->
                city.cityId
            }.toSet()
        }
}
