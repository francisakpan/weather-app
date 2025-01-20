package com.francis.weatherap.data.repository.city.impl.model

import com.google.gson.annotations.SerializedName


data class MetadataModel(

    @SerializedName("currentOffset")
    val currentOffset: Int? = null,

    @SerializedName("totalCount")
    val totalCount: Int? = null,
)
