package com.francis.weatherap.data.repository.city.impl.model

import com.google.gson.annotations.SerializedName

data class LinksModel(

    @SerializedName("rel")
    val rel: String? = null,

    @SerializedName("href")
    val href: String? = null,
)
