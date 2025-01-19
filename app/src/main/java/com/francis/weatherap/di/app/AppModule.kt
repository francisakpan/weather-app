package com.francis.weatherap.di.app

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson().newBuilder()
            .setLenient()
            .create()
    }
}