package data.model

data class TwoDaysWeather(
    val prefecture: Prefecture,
    val today: OneDayWeather,
    val tomorrow: OneDayWeather
)
