package com.francis.weatherap.data.repository.weather.impl.model.forecast

import com.francis.weatherap.data.repository.weather.impl.model.CurrentModel
import com.francis.weatherap.data.repository.weather.impl.model.LocationModel
import com.google.gson.annotations.SerializedName

data class ForecastModel(

    @SerializedName("current")
    val current: CurrentModel? = null,

    @SerializedName("location")
    val location: LocationModel? = null,

    @SerializedName("forecast")
    val forecast: ForecastDaysModel? = null,
)
