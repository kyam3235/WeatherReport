package data.model

data class TwoDaysWeather(
    val dateEpoch: Long,
    val city: City,
    val today: OneDayWeather,
    val tomorrow: OneDayWeather
)
