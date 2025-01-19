package com.francis.weatherap.data.repository.city.impl

import android.util.LruCache
import com.francis.weatherap.core.types.either.fold
import com.francis.weatherap.core.types.location.Coordinates
import com.francis.weatherap.core.util.safeApiCall
import com.francis.weatherap.data.repository.city.dif.CitiesException
import com.francis.weatherap.data.repository.city.dif.CityRepository
import com.francis.weatherap.data.repository.city.dif.model.CitySearchResponse
import com.francis.weatherap.di.dispatchers.DispatcherProvider
import com.francis.weatherap.framework.network.city.CityService
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

private const val CacheCapacity = 40

private data class PrefixCacheKey(
    val prefix: String,
    val offset: Int,
    val limit: Int,
)

private data class LocationCacheKey(
    val location: Coordinates,
    val offset: Int,
    val limit: Int,
)

class CityRepositoryImpl @Inject constructor(
    private val cityService: CityService,
    private val dispatcherProvider: DispatcherProvider,
) : CityRepository {

    private val prefixCache = LruCache<PrefixCacheKey, CitySearchResponse>(CacheCapacity)
    private val locationCache = LruCache<LocationCacheKey, CitySearchResponse>(CacheCapacity)

    override suspend fun getCitiesByPrefix(
        prefix: String,
        offset: Int,
        limit: Int,
    ): CitySearchResponse {
        val prefixCacheKey = PrefixCacheKey(prefix = prefix, offset = offset, limit = limit)
        prefixCache[prefixCacheKey]?.let { return it }

        val networkResponse = safeApiCall {
            cityService.getCitiesByPrefix(prefix, offset, limit)
        }
        return networkResponse.fold(
            onSuccess = { response ->
                if (response.isValid) {
                    withContext(dispatcherProvider.default) {
                        response.toCityResponse().also { prefixCache.put(prefixCacheKey, it) }
                    }
                } else {
                    throw CitiesException("Invalid data received")
                }
            },
            onFailure = { throwable ->
                throw CitiesException(cause = throwable)
            },
        )
    }

    override suspend fun getCitiesByLocation(location: Coordinates, offset: Int, limit: Int): CitySearchResponse {
        val locationCacheKey = LocationCacheKey(location = location, offset = offset, limit = limit)
        locationCache[locationCacheKey]?.let { return it }

        val networkResponse = safeApiCall {
            cityService.getCitiesByLocation(location.asIso6709, offset, limit)
        }
        return networkResponse.fold(
            onSuccess = { response ->
                if (response.isValid) {
                    withContext(dispatcherProvider.default) {
                        response.toCityResponse().also { locationCache.put(locationCacheKey, it) }
                    }
                } else {
                    throw CitiesException("Invalid data received")
                }
            },
            onFailure = { throwable ->
                throw CitiesException(cause = throwable)
            },
        )
    }
}
