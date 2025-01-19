package com.francis.weatherap.domain.weather

import com.francis.weatherap.core.time.def.Iso8601DateTime
import com.francis.weatherap.core.types.weather.AverageVisibility
import com.francis.weatherap.core.types.weather.Humidity
import com.francis.weatherap.core.types.weather.Precipitation
import com.francis.weatherap.core.types.weather.Pressure
import com.francis.weatherap.core.types.weather.Speed
import com.francis.weatherap.core.types.weather.Temperature
import com.francis.weatherap.core.types.weather.UvIndex
import com.francis.weatherap.data.interactors.weather.WeatherLocation
import com.francis.weatherap.data.interactors.weather.model.Current
import com.francis.weatherap.data.repository.weather.dif.model.Current as RepoCurrent
import com.francis.weatherap.data.interactors.weather.model.ForecastEntry
import com.francis.weatherap.data.interactors.weather.model.TodayClouds
import com.francis.weatherap.data.interactors.weather.model.TodayMain
import com.francis.weatherap.data.interactors.weather.model.TodayWind
import com.francis.weatherap.data.repository.weather.dif.model.forecast.ForecastHour
import com.francis.weatherap.data.repository.weather.dif.model.today.TodayWeatherResponse
import java.time.ZoneId
import kotlin.math.roundToInt
import com.francis.weatherap.data.repository.weather.dif.WeatherLocation as RepositoryLocation

internal fun WeatherLocation.toRepositoryLocation() = when (this) {
    is WeatherLocation.City -> RepositoryLocation.City(
        name = name,
        countryCode = countryCode,
    )

    is WeatherLocation.Location -> RepositoryLocation.Location(
        coordinates = coordinates,
    )
}

internal fun TodayWeatherResponse.toTodayMain(): TodayMain =
    TodayMain(
        code = current.condition.code,
        description = current.condition.text,
        temperature = Temperature.fromCelsius(current.tempCelsius),
        feelsLike = Temperature.fromCelsius(current.feelsLikeCelsius),
        humidity = Humidity(current.humidity),
        pressure = Pressure.fromMillibars(current.pressureMb),
        precipitation = Precipitation.fromMillimeters(current.precipitationMm),
        uvIndex = UvIndex(current.uvIndex.roundToInt()),
    )

internal fun TodayWeatherResponse.toTodayWind(): TodayWind =
    TodayWind(
        direction = current.windDirection,
        speed = Speed.fromKph(current.windKph),
        gust = Speed.fromKph(current.gustKph),
    )

internal fun TodayWeatherResponse.toTodayClouds(): TodayClouds =
    TodayClouds(
        all = current.cloud,
    )

internal fun RepoCurrent.toCurrent(): Current = Current(
    description = condition.text,
    code = condition.code,
    temperature = Temperature.fromCelsius(tempCelsius),
    feelsLike = Temperature.fromCelsius(feelsLikeCelsius),
    wind = Speed.fromKph(windKph),
    gust = Speed.fromKph(gustKph),
    humidity = Humidity(humidity),
    pressure = Pressure.fromMillibars(pressureMb),
    precipitation = Precipitation.fromMillimeters(precipitationMm),
    uvIndex = UvIndex(uvIndex.roundToInt()),
    visibility = AverageVisibility.fromKm(visibilityKm),
    iconCode = condition.code,
)

internal fun ForecastHour.toForecastEntry(zoneId: ZoneId) = ForecastEntry(
    zonedDateTime = this.time.let { date -> Iso8601DateTime(date).toZonedDateTime(zoneId = zoneId) },
    description = condition.text,
    iconCode = condition.code,
    temperature = Temperature.fromCelsius(tempCelsius),
    feelsLike = Temperature.fromCelsius(feelsLikeCelsius),
    precipitation = Precipitation.fromMillimeters(precipitationMm),
    windSpeed = Speed.fromKph(windKph),
    uvIndex = UvIndex(uvIndex.roundToInt()),
    humidity = Humidity(humidity),
    visibility = AverageVisibility.fromKm(visibilityKm),
)
