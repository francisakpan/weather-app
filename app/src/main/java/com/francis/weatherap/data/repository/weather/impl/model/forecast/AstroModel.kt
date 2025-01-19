package com.francis.weatherap.data.repository.weather.impl.model.forecast

import com.google.gson.annotations.SerializedName


data class AstroModel(

    @SerializedName("moonset")
    val moonset: String? = null,

    @SerializedName("moon_illumination")
    val moonIllumination: Double? = null,

    @SerializedName("sunrise")
    val sunrise: String? = null,

    @SerializedName("moon_phase")
    val moonPhase: String? = null,

    @SerializedName("sunset")
    val sunset: String? = null,

    @SerializedName("moonrise")
    val moonrise: String? = null,
)
