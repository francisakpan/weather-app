package com.francis.weatherap.framework.paging

import androidx.paging.PagingSource
import com.francis.weatherap.core.types.location.Coordinates
import com.francis.weatherap.data.repository.city.dif.model.City

interface CitiesPagingSourceFactory {
    fun create(params: Params): PagingSource<Int, City>

    sealed interface Params {
        data class Prefix(val prefix: String) : Params
        data class Location(val coordinates: Coordinates) : Params
    }
}
