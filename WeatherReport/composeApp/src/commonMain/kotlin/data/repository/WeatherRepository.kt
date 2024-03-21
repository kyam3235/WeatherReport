package data.repository

import data.model.Prefecture
import data.model.TwoDaysWeather

interface WeatherRepository {
    suspend fun getTwoDaysWeather(prefecture: Prefecture): TwoDaysWeather
}