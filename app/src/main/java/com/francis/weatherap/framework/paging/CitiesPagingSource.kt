package com.francis.weatherap.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.francis.weatherap.data.repository.city.dif.CitiesException
import com.francis.weatherap.data.repository.city.dif.CityRepository
import com.francis.weatherap.data.repository.city.dif.model.City
import java.util.concurrent.CancellationException

internal class CitiesPagingSource(
    private val queryParams: CitiesPagingSourceFactory.Params,
    private val cityRepository: CityRepository,
) : PagingSource<Int, City>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, City> = try {
        val offset = params.key ?: 0
        val response = when (queryParams) {
            is CitiesPagingSourceFactory.Params.Prefix -> cityRepository.getCitiesByPrefix(
                prefix = queryParams.prefix,
                offset = offset,
                limit = params.loadSize,
            )
            is CitiesPagingSourceFactory.Params.Location -> cityRepository.getCitiesByLocation(
                location = queryParams.coordinates,
                offset = offset,
                limit = params.loadSize,
            )
        }
        val hasMore = response.metadata.currentOffset + params.loadSize < response.metadata.totalCount
        LoadResult.Page(
            data = response.cities,
            prevKey = if (response.metadata.currentOffset > 0) response.metadata.currentOffset else null,
            nextKey = if (hasMore) response.metadata.currentOffset + params.loadSize else null,
        )
    } catch (ex: CancellationException) {
        throw ex
    } catch (e: CitiesException) {
        LoadResult.Error(e)
    }

    override fun getRefreshKey(state: PagingState<Int, City>): Int? = state.anchorPosition?.let { anchorPosition ->
        state.closestPageToPosition(anchorPosition)?.prevKey?.plus(state.config.pageSize)
            ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(state.config.pageSize)
    }
}
