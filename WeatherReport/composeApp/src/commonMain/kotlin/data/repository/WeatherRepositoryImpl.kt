package data.repository

import data.api.FreeWeatherApi
import data.api.request.ForecastRequest
import data.model.OneDayWeather
import data.model.City
import data.model.TwoDaysWeather
import kotlin.math.roundToInt

class WeatherRepositoryImpl(
    private val weatherApi: FreeWeatherApi
) : WeatherRepository {
    override suspend fun getTwoDaysWeather(city: City): TwoDaysWeather {
        val request = ForecastRequest(
            city = city,
            days = 2
        )
        val response = weatherApi.forecast(request)
        return TwoDaysWeather(
            city = city,
            today = OneDayWeather(
                iconUrl = response.forecast.forecastDays[0].day.condition.icon,
                text = response.forecast.forecastDays[0].day.condition.text,
                maxTemperatureCelsius = response.forecast.forecastDays[0].day.maxTemperatureCelsius.roundToInt(),
                minTemperatureCelsius = response.forecast.forecastDays[0].day.minTemperatureCelsius.roundToInt(),
                dailyChanceOfRain = response.forecast.forecastDays[0].day.dailyChanceOfRain.roundToInt()
            ),
            tomorrow = OneDayWeather(
                iconUrl = response.forecast.forecastDays[1].day.condition.icon,
                text = response.forecast.forecastDays[1].day.condition.text,
                maxTemperatureCelsius = response.forecast.forecastDays[1].day.maxTemperatureCelsius.roundToInt(),
                minTemperatureCelsius = response.forecast.forecastDays[1].day.minTemperatureCelsius.roundToInt(),
                dailyChanceOfRain = response.forecast.forecastDays[1].day.dailyChanceOfRain.roundToInt()
            )
        )
    }
}