package com.francis.weatherap.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.francis.weatherap.framework.database.dao.FavoriteCitiesDao
import com.francis.weatherap.framework.database.entity.FavoriteCityEntity

@Database(entities = [FavoriteCityEntity::class], version = 1, exportSchema = true)
abstract class FavoriteCitiesDatabase : RoomDatabase() {
    abstract fun favoriteCitiesDao(): FavoriteCitiesDao
}
