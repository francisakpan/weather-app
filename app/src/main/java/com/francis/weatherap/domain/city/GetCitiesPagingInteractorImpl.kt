package com.francis.weatherap.domain.city

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.francis.weatherap.core.types.location.Coordinates
import com.francis.weatherap.data.interactors.city.GetCitiesPagingInteractor
import com.francis.weatherap.data.interactors.city.model.City
import com.francis.weatherap.framework.paging.CitiesPagingSourceFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCitiesPagingInteractorImpl @Inject constructor(
    private val citiesPagingSourceFactory: CitiesPagingSourceFactory,
) : GetCitiesPagingInteractor() {
    override fun buildStream(params: Parameters): Flow<PagingData<City>> = Pager(config = params.pagingConfig) {
        citiesPagingSourceFactory.create(
            params.toPagingSourceParams(),
        )
    }
        .flow
        .map { pagingData ->
            pagingData.map { city ->
                city.toCity()
            }
        }

    private fun Parameters.toPagingSourceParams() = when (this) {
        is Parameters.CoordinateParameters -> CitiesPagingSourceFactory.Params.Location(
            coordinates = Coordinates(
                longitude = coordinates.longitude,
                latitude = coordinates.latitude,
            ),
        )

        is Parameters.PrefixParameters -> CitiesPagingSourceFactory.Params.Prefix(
            prefix = prefix,
        )
    }
}
