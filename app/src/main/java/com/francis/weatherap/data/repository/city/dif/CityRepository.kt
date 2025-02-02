package com.francis.weatherap.data.repository.city.dif

import com.francis.weatherap.core.types.location.Coordinates
import com.francis.weatherap.data.repository.city.dif.model.CitySearchResponse

/** Repository for cities */
interface CityRepository {
    /**
     * Gets a list of cities matching the [prefix]
     *
     * @param prefix prefix to filter cities by
     * @param offset page offset
     * @param limit maximum number of results to return
     * @return a [CitySearchResponse]
     */
    suspend fun getCitiesByPrefix(
        prefix: String = "",
        offset: Int = 0,
        limit: Int = 10,
    ): CitySearchResponse

    /**
     * Gets a list of cities based on the location [Coordinates]]
     *
     * @param location the [Coordinates] for the cities load request
     * @param offset page offset
     * @param limit maximum number of results to return
     * @return a [CitySearchResponse]
     */
    suspend fun getCitiesByLocation(
        location: Coordinates,
        offset: Int = 0,
        limit: Int = 10,
    ): CitySearchResponse
}
