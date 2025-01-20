package com.francis.weatherap.data.repository.favorite

import com.francis.weatherap.data.repository.favorite.model.FavoriteCity
import com.francis.weatherap.framework.database.dao.FavoriteCitiesDao
import com.francis.weatherap.framework.database.FavoriteCitiesDatabase
import com.francis.weatherap.framework.database.entity.FavoriteCityEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteCitiesDatabase: FavoriteCitiesDatabase,
) : FavoriteRepository {

    private val dao: FavoriteCitiesDao
        get() = favoriteCitiesDatabase.favoriteCitiesDao()

    override fun getFavoriteCities(): Flow<List<FavoriteCity>> = dao.getFavoriteCities().map { cities ->
        cities.map { city ->
            FavoriteCity(
                cityId = city.cityId,
                name = city.name,
                countryCode = city.countryCode,
            )
        }
    }

    override suspend fun saveFavoriteCity(favoriteCity: FavoriteCity) = dao.insertFavoriteCity(
        FavoriteCityEntity(
            cityId = favoriteCity.cityId,
            name = favoriteCity.name,
            countryCode = favoriteCity.countryCode,
        ),
    )

    override suspend fun deleteFavoriteCity(cityId: Long) = dao.deleteFavoriteCity(
        cityId,
    )
}
