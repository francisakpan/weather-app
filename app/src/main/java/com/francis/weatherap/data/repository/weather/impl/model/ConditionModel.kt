package com.francis.weatherap.data.repository.weather.impl.model

import com.google.gson.annotations.SerializedName


data class ConditionModel(

    @SerializedName("code")
    val code: Int? = null,

    @SerializedName("icon")
    val icon: String? = null,

    @SerializedName("text")
    val text: String? = null,
)
