package data.model

data class TwoDaysWeather(
    val city: City,
    val today: OneDayWeather,
    val tomorrow: OneDayWeather
)
