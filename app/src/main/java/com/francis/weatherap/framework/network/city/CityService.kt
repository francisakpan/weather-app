package com.francis.weatherap.framework.network.city

import com.francis.weatherap.data.repository.city.impl.model.CitySearchResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CityService {
    @GET("v1/geo/cities")
    suspend fun getCitiesByPrefix(
        @Query("namePrefix") namePrefix: String?,
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int?,
    ): Response<CitySearchResponseModel>

    @GET("v1/geo/cities")
    suspend fun getCitiesByLocation(
        @Query("location") location: String?,
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int?,
    ): Response<CitySearchResponseModel>
}
