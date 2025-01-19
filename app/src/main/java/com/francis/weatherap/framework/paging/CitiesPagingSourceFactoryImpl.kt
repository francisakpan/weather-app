package com.francis.weatherap.framework.paging

import androidx.paging.PagingSource
import com.francis.weatherap.data.repository.city.dif.CityRepository
import com.francis.weatherap.data.repository.city.dif.model.City
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class CitiesPagingSourceFactoryImpl @Inject constructor(
    private val cityRepository: CityRepository,
) : CitiesPagingSourceFactory {
    override fun create(params: CitiesPagingSourceFactory.Params): PagingSource<Int, City> = CitiesPagingSource(
        queryParams = params,
        cityRepository = cityRepository,
    )
}
