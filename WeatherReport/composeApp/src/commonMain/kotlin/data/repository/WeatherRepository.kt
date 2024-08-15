package data.repository

import data.model.City
import data.model.CurrentLocation
import data.model.ForecastWeather
import data.model.TwoDaysWeather

interface WeatherRepository {
    suspend fun getTwoDaysWeather(city: City): TwoDaysWeather

    suspend fun getCurrentWeather(location: CurrentLocation): ForecastWeather
}