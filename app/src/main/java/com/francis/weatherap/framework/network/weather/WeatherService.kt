package com.francis.weatherap.framework.network.weather

import com.francis.weatherap.data.repository.weather.impl.model.forecast.ForecastModel
import com.francis.weatherap.data.repository.weather.impl.model.today.TodayWeatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather")
    suspend fun getTodayWeather(
        @Query("q") query: String? = null,
        @Query("units") units: String = "standard",
        @Query("appid") appId: String = "da0f9c8d90bde7e619c3ec47766a42f4"
    ): Response<TodayWeatherModel>

    @GET("forecast.json")
    suspend fun getForecast(
        @Query("q") query: String? = null,
        @Query("cnt") days: Int = 7,
        @Query("units") units: String = "standard",
        @Query("appid") appId: String = "da0f9c8d90bde7e619c3ec47766a42f4"
    ): Response<ForecastModel>
}
