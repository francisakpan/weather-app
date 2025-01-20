package com.francis.weatherap.data.interactors.city

import com.francis.weatherap.core.foundation.Interactor
import com.francis.weatherap.core.types.either.Either
import com.francis.weatherap.data.interactors.city.model.Cities


/** Gets a list of cities matching the prefix */
interface GetCitiesInteractor : Interactor<GetCitiesInteractor.Params, Either<Cities>> {

    /**
     * Configuration parameters for [GetCitiesInteractor]
     *
     * @param prefix prefix to filter cities
     * @param limit max number of results to return
     */
    data class Params(
        val prefix: String,
        val limit: Int = 10,
    )
}
