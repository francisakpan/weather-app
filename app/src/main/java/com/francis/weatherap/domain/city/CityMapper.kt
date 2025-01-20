package com.francis.weatherap.domain.city

import com.francis.weatherap.data.interactors.city.model.Cities
import com.francis.weatherap.data.interactors.city.model.City
import com.francis.weatherap.data.interactors.city.model.FavoriteCity
import com.francis.weatherap.data.interactors.city.model.Metadata
import com.francis.weatherap.data.repository.city.dif.model.CitySearchResponse
import com.francis.weatherap.data.repository.city.dif.model.City as RepoCity
import com.francis.weatherap.data.repository.city.dif.model.Metadata as RepoMetadata
import com.francis.weatherap.data.repository.favorite.model.FavoriteCity as RepoFavoriteCity

internal fun CitySearchResponse.toCities(): Cities = Cities(
    metadata = metadata.toMetadata(),
    cities = cities.toCities(),
)

internal fun RepoMetadata.toMetadata(): Metadata = Metadata(
    offset = currentOffset,
    totalCount = totalCount,
)

internal fun List<RepoCity>.toCities(): List<City> = map { city -> city.toCity() }

internal fun RepoCity.toCity(): City = City(
    id = id,
    name = name,
    region = region,
    regionCode = regionCode,
    country = country,
    countryCode = countryCode,
    coordinates = coordinates,
)

internal fun RepoFavoriteCity.toFavoriteCity(): FavoriteCity = FavoriteCity(
    cityId = cityId,
    name = name,
    countryCode = countryCode,
)

internal fun FavoriteCity.toRepoFavoriteCity(): RepoFavoriteCity = RepoFavoriteCity(
    cityId = cityId,
    name = name,
    countryCode = countryCode,
)
