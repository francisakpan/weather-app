package com.francis.weatherap.domain.city

import com.francis.weatherap.core.types.either.Either
import com.francis.weatherap.data.interactors.city.CitiesException
import com.francis.weatherap.data.interactors.city.GetCitiesInteractor
import com.francis.weatherap.data.interactors.city.model.Cities
import com.francis.weatherap.data.repository.city.dif.CityRepository
import com.francis.weatherap.di.dispatchers.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.francis.weatherap.data.repository.city.dif.CitiesException as RepoCitiesException

class GetCitiesInteractorImpl @Inject constructor(
    private val cityRepository: CityRepository,
    private val dispatcherProvider: DispatcherProvider,
) : GetCitiesInteractor {

    override suspend fun invoke(params: GetCitiesInteractor.Params): Either<Cities> = try {
        val cities = cityRepository.getCitiesByPrefix(
            prefix = params.prefix,
            offset = 0,
            limit = params.limit,
        )
        withContext(dispatcherProvider.default) {
            Either.Success(cities.toCities())
        }
    } catch (ex: RepoCitiesException) {
        Either.Failure(
            CitiesException(
                message = ex.message ?: "Error fetching cities",
                cause = ex.cause,
            ),
        )
    }
}
