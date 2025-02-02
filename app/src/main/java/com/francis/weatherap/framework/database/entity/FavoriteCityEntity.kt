package com.francis.weatherap.framework.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteCityEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("city_id")
    val cityId: Long,
    val name: String,
    @ColumnInfo("country_code")
    val countryCode: String,
)
