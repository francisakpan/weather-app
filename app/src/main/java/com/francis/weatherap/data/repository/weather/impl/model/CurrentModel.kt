package com.francis.weatherap.data.repository.weather.impl.model

import com.google.gson.annotations.SerializedName


data class CurrentModel(

    @SerializedName("feelslike_c")
    val feelsLikeC: Double? = null,

    @SerializedName("uv")
    val uv: Double? = null,

    @SerializedName("last_updated")
    val lastUpdated: String? = null,

    @SerializedName("feelslike_f")
    val feelsLikeF: Double? = null,

    @SerializedName("wind_degree")
    val windDegree: Int? = null,

    @SerializedName("last_updated_epoch")
    val lastUpdatedEpoch: Int? = null,

    @SerializedName("is_day")
    val isDay: Int? = null,

    @SerializedName("precip_in")
    val precipIn: Double? = null,

    @SerializedName("wind_dir")
    val windDir: String? = null,

    @SerializedName("gust_mph")
    val gustMph: Double? = null,

    @SerializedName("temp_c")
    val tempC: Double? = null,

    @SerializedName("pressure_in")
    val pressureIn: Double? = null,

    @SerializedName("gust_kph")
    val gustKph: Double? = null,

    @SerializedName("temp_f")
    val tempF: Double? = null,

    @SerializedName("precip_mm")
    val precipMm: Double? = null,

    @SerializedName("cloud")
    val cloud: Int? = null,

    @SerializedName("wind_kph")
    val windKph: Double? = null,

    @SerializedName("condition")
    val condition: ConditionModel? = null,

    @SerializedName("wind_mph")
    val windMph: Double? = null,

    @SerializedName("vis_km")
    val visKm: Double? = null,

    @SerializedName("humidity")
    val humidity: Int? = null,

    @SerializedName("pressure_mb")
    val pressureMb: Double? = null,

    @SerializedName("vis_miles")
    val visMiles: Double? = null,
)
