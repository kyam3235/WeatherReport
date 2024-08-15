package data.repository

import data.api.FreeWeatherApi
import data.api.request.ForecastRequest
import data.model.City
import data.model.CurrentLocation
import data.model.OneDayWeather
import data.model.TwoDaysWeather
import kotlin.math.roundToInt

class WeatherRepositoryImpl(
    private val weatherApi: FreeWeatherApi
) : WeatherRepository {
    override suspend fun getTwoDaysWeather(city: City): TwoDaysWeather {
        val request = ForecastRequest.City(
            city = city,
            days = 2
        )
        val response = weatherApi.forecast(request)
        return TwoDaysWeather(
            dateEpoch = response.forecast.forecastDays[0].dateEpoch,
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

    override suspend fun getCurrentWeather(location: CurrentLocation): TwoDaysWeather {
        val request = ForecastRequest.Location(
            location = location,
            days = 3
        )
        val response = weatherApi.forecast(request)
        return TwoDaysWeather(
            dateEpoch = response.forecast.forecastDays[0].dateEpoch,
            city = City.NAGOYA,
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