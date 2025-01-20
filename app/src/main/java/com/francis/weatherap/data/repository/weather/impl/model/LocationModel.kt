package com.francis.weatherap.data.repository.weather.impl.model

import com.google.gson.annotations.SerializedName


data class LocationModel(

    @SerializedName("localtime")
    val localtime: String? = null,

    @SerializedName("country")
    val country: String? = null,

    @SerializedName("localtime_epoch")
    val localtimeEpoch: Int? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("lon")
    val lon: Double? = null,

    @SerializedName("region")
    val region: String? = null,

    @SerializedName("lat")
    val lat: Double? = null,

    @SerializedName("tz_id")
    val tzId: String? = null,
)
