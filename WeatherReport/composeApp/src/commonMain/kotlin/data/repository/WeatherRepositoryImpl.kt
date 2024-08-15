package data.repository

import data.api.FreeWeatherApi
import data.api.request.ForecastRequest
import data.model.City
import data.model.CurrentInfo
import data.model.CurrentLocation
import data.model.DayInfo
import data.model.ForecastInfo
import data.model.ForecastWeather
import data.model.LocationInfo
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

    override suspend fun getCurrentWeather(location: CurrentLocation): ForecastWeather {
        val request = ForecastRequest.Location(
            location = location,
            days = 3
        )
        val response = weatherApi.forecast(request)
        return ForecastWeather(
            locationInfo = LocationInfo(
                name = response.location.name,
                region = response.location.region,
                country = response.location.country,
                latitude = response.location.latitude,
                longitude = response.location.longitude,
                localtimeEpoch = response.location.localtimeEpoch
            ),
            currentInfo = CurrentInfo(
                lastUpdatedEpoch = response.current.lastUpdatedEpoch,
                temperature = response.current.temperatureCelsius,
                conditionInfo = data.model.ConditionInfo(
                    text = response.current.condition.text,
                    icon = response.current.condition.icon
                )
            ),
            forecastInfos = response.forecast.forecastDays.map { forecastDay ->
                ForecastInfo(
                    dateEpoch = forecastDay.dateEpoch,
                    dayInfo = DayInfo(
                        maxTemperature = forecastDay.day.maxTemperatureCelsius.roundToInt(),
                        minTemperature = forecastDay.day.minTemperatureCelsius.roundToInt(),
                        chanceOfRain = forecastDay.day.dailyChanceOfRain.roundToInt(),
                        conditionInfo = data.model.ConditionInfo(
                            text = forecastDay.day.condition.text,
                            icon = forecastDay.day.condition.icon
                        )
                    )
                )
            }
        )
    }
}