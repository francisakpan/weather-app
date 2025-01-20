package com.francis.weatherap.data.repository.weather.impl.model.forecast

import com.francis.weatherap.data.repository.weather.impl.model.ConditionModel
import com.google.gson.annotations.SerializedName

data class DayModel(

    @SerializedName("avgvis_km")
    val avgvisKm: Double? = null,

    @SerializedName("uv")
    val uv: Double? = null,

    @SerializedName("avgtemp_f")
    val avgtempF: Double? = null,

    @SerializedName("avgtemp_c")
    val avgtempC: Double? = null,

    @SerializedName("daily_chance_of_snow")
    val dailyChanceOfSnow: Int? = null,

    @SerializedName("maxtemp_c")
    val maxtempC: Double? = null,

    @SerializedName("maxtemp_f")
    val maxtempF: Double? = null,

    @SerializedName("mintemp_c")
    val mintempC: Double? = null,

    @SerializedName("avgvis_miles")
    val avgvisMiles: Double? = null,

    @SerializedName("daily_will_it_rain")
    val dailyWillItRain: Int? = null,

    @SerializedName("mintemp_f")
    val mintempF: Double? = null,

    @SerializedName("totalprecip_in")
    val totalprecipIn: Double? = null,

    @SerializedName("avghumidity")
    val avghumidity: Double? = null,

    @SerializedName("condition")
    val condition: ConditionModel? = null,

    @SerializedName("maxwind_kph")
    val maxwindKph: Double? = null,

    @SerializedName("maxwind_mph")
    val maxwindMph: Double? = null,

    @SerializedName("daily_chance_of_rain")
    val dailyChanceOfRain: Int? = null,

    @SerializedName("totalprecip_mm")
    val totalprecipMm: Double? = null,

    @SerializedName("daily_will_it_snow")
    val dailyWillItSnow: Int? = null,
)
