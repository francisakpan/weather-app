package com.francis.weatherap.data.repository.city.impl.model

import com.google.gson.annotations.SerializedName


data class CityModel(

    @SerializedName("country")
    val country: String? = null,

    @SerializedName("wikiDataId")
    val wikiDataId: String? = null,

    @SerializedName("regionCode")
    val regionCode: String? = null,

    @SerializedName("city")
    val city: String? = null,

    @SerializedName("countryCode")
    val countryCode: String? = null,

    @SerializedName("latitude")
    val latitude: Double? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("type")
    val type: String? = null,

    @SerializedName("region")
    val region: String? = null,

    @SerializedName("longitude")
    val longitude: Double? = null,
)
