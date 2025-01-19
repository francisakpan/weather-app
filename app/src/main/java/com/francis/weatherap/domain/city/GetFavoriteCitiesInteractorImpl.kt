package com.francis.weatherap.domain.city

import com.francis.weatherap.data.interactors.city.GetFavoriteCitiesInteractor
import com.francis.weatherap.data.interactors.city.model.FavoriteCity
import com.francis.weatherap.data.repository.favorite.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavoriteCitiesInteractorImpl @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
) : GetFavoriteCitiesInteractor() {

    override fun buildStream(params: Unit): Flow<List<FavoriteCity>> = favoriteRepository
        .getFavoriteCities()
        .map { cities ->
            cities.map { city ->
                city.toFavoriteCity()
            }
        }
}
