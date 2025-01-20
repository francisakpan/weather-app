package com.francis.weatherap.data.repository.weather.impl.model.forecast

import com.google.gson.annotations.SerializedName

data class ForecastDayModel(

    @SerializedName("date")
    val date: String? = null,

    @SerializedName("date_epoch")
    val dateEpoch: Int? = null,

    @SerializedName("astro")
    val astro: AstroModel? = null,

    @SerializedName("day")
    val day: DayModel? = null,

    @SerializedName("hour")
    val hour: List<HourModel>? = null,
)
