package com.francis.weatherap.data.interactors.city

import androidx.paging.PagingConfig
import com.francis.weatherap.data.interactors.city.model.City
import com.francis.weatherap.core.foundation.PagingInteractor
import com.francis.weatherap.core.types.location.Coordinates

/** Loads paginated cities */
abstract class GetCitiesPagingInteractor : PagingInteractor<GetCitiesPagingInteractor.Parameters, City>() {

    sealed interface Parameters : PagingInteractor.Parameters {
        /**
         * Configuration parameters for [GetCitiesPagingInteractor]
         *
         * @property pagingConfig the [PagingConfig] for the data load
         * @property prefix the city prefix for the search query
         */
        data class PrefixParameters(
            override val pagingConfig: PagingConfig,
            val prefix: String,
        ) : Parameters

        /**
         * Configuration parameters for [GetCitiesPagingInteractor]
         *
         * @property pagingConfig the [PagingConfig] for the data load
         * @property coordinates the coordinates for the cities load request
         */
        data class CoordinateParameters(
            override val pagingConfig: PagingConfig,
            val coordinates: Coordinates,
        ) : Parameters
    }
}
