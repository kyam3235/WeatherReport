package data.model

data class OneDayWeather(
    val iconUrl: String,
    val text: String,
    val maxTemperatureCelsius: Int,
    val minTemperatureCelsius: Int,
    val dailyChanceOfRain: Int
)
