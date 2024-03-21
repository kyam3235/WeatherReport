@file:Suppress("PLUGIN_IS_NOT_ENABLED")

package data.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastResponse(
    val location: Location,
    val current: Current,
    val forecast: Forecast
)

@Serializable
@SerialName("location")
data class Location(
    @SerialName("name")
    val name: String,
    @SerialName("region")
    val region: String,
    @SerialName("country")
    val country: String,
    @SerialName("lat")
    val latitude: Double,
    @SerialName("lon")
    val longitude: Double,
    @SerialName("tz_id")
    val timeZoneId: String,
    @SerialName("localtime_epoch")
    val localtimeEpoch: Long,
    @SerialName("localtime")
    val localtime: String
)

@Serializable
@SerialName("current")
data class Current(
    @SerialName("last_updated_epoch")
    val lastUpdatedEpoch: Long,
    @SerialName("last_updated")
    val lastUpdated: String,
    @SerialName("temp_c")
    val temperatureCelsius: Double,
    @SerialName("temp_f")
    val temperatureFahrenheit: Double,
    @SerialName("is_day")
    val isDay: Int,
    val condition: Condition,
    @SerialName("wind_mph")
    val windMilePerHour: Double,
    @SerialName("wind_kph")
    val windKmPerHour: Double,
    @SerialName("wind_degree")
    val windDegree: Double,
    @SerialName("wind_dir")
    val windDirection: String,
    @SerialName("pressure_mb")
    val pressureMilliBars: Double,
    @SerialName("pressure_in")
    val pressureInches: Double,
    @SerialName("precip_mm")
    val precipitationMilliMeters: Double,
    @SerialName("precip_in")
    val precipitationInches: Double,
    @SerialName("cloud")
    val cloud: Double,
    @SerialName("feelslike_c")
    val feelslikeCelsius: Double,
    @SerialName("feelslike_f")
    val feelslikeFahrenheit: Double,
    @SerialName("vis_km")
    val visibilityInKilometer: Double,
    @SerialName("vis_miles")
    val visibilityInMiles: Double,
    @SerialName("uv")
    val uv: Double,
    @SerialName("gust_mph")
    val windGustInMilesPerHour: Double,
    @SerialName("gust_kph")
    val windGustInKmPerHour: Double
)

@Serializable
@SerialName("condition")
data class Condition(
    @SerialName("text")
    val text: String,
    @SerialName("icon")
    val icon: String,
    @SerialName("code")
    val code: Int
)

@Serializable
@SerialName("forecast")
data class Forecast(
    @SerialName("forecastday")
    val forecastDays: List<ForecastDay>
)

@Serializable
data class ForecastDay(
    @SerialName("date")
    val date: String,
    @SerialName("date_epoch")
    val dateEpoch: Long,
    val day: Day
)

@Serializable
@SerialName("day")
data class Day(
    @SerialName("maxtemp_c")
    val maxTemperatureCelsius: Double,
    @SerialName("mintemp_c")
    val minTemperatureCelsius: Double,
    @SerialName("daily_will_it_rain")
    val dailyWillItRain: Int,
    @SerialName("daily_chance_of_rain")
    val dailyChanceOfRain: Double,
    @SerialName("daily_will_it_snow")
    val dailyWillItSnow: Int,
    @SerialName("daily_chance_of_snow")
    val dailyChanceOfSnow: Double,
    @SerialName("condition")
    val condition: Condition
)
