package com.francis.weatherap.data.interactors.weather

import com.francis.weatherap.core.types.location.Coordinates


/** The location to get weather for */
sealed interface WeatherLocation {
    /**
     * A location specified as a city name and country code
     *
     * @property name the city name
     * @property countryCode the 2 digits country code
     */
    data class City(
        val name: String,
        val countryCode: String,
    ) : WeatherLocation

    /**
     * A location specified as latitude and longitude coordinates
     *
     * @property coordinates the weather coordinates
     */
    data class Location(
        val coordinates: Coordinates,
    ) : WeatherLocation
}
