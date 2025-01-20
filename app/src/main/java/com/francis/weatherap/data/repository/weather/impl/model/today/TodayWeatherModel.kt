package com.francis.weatherap.data.repository.weather.impl.model.today

import com.francis.weatherap.data.repository.weather.impl.model.CurrentModel
import com.francis.weatherap.data.repository.weather.impl.model.LocationModel
import com.google.gson.annotations.SerializedName

data class TodayWeatherModel(

    @SerializedName("current")
    val current: CurrentModel? = null,

    @SerializedName("location")
    val location: LocationModel? = null,
)
