package data.repository

import data.model.City
import data.model.TwoDaysWeather

interface WeatherRepository {
    suspend fun getTwoDaysWeather(city: City): TwoDaysWeather
}