package com.francis.weatherap.data.interactors.city.model

/**
 * Cities response metadata for pagination
 *
 * @property offset this response's offset
 * @property totalCount total number of results
 */
data class Metadata(
    val offset: Int,
    val totalCount: Int,
)
