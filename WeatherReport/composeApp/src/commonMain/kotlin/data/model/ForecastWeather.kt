@file:Suppress("PLUGIN_IS_NOT_ENABLED")

package data.model

data class ForecastWeather(
    val locationInfo: LocationInfo,
    val currentInfo: CurrentInfo,
    val forecastInfos: List<ForecastInfo>
)

data class LocationInfo(
    val name: String,
    val region: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val localtimeEpoch: Long
)

data class CurrentInfo(
    val lastUpdatedEpoch: Long,
    val temperature: Double,
    val conditionInfo: ConditionInfo
)

data class ConditionInfo(
    val text: String,
    val icon: String
)

data class ForecastInfo(
    val dateEpoch: Long,
    val dayInfo: DayInfo
)

data class DayInfo(
    val maxTemperature: Int,
    val minTemperature: Int,
    val chanceOfRain: Int,
    val conditionInfo: ConditionInfo
)
