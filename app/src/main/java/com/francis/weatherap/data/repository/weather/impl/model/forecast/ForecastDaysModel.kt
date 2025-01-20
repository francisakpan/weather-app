package com.francis.weatherap.data.repository.weather.impl.model.forecast

import com.google.gson.annotations.SerializedName

data class ForecastDaysModel(

    @SerializedName("forecastday")
    val forecastDay: List<ForecastDayModel>? = null,
)
