package com.francis.weatherap.data.repository.city.impl.model

import com.google.gson.annotations.SerializedName

data class CitySearchResponseModel(

    @SerializedName("metadata")
    val metadata: MetadataModel? = null,

    @SerializedName("data")
    val data: List<CityModel>? = null,

    @SerializedName("links")
    val links: List<LinksModel>? = null,
)
