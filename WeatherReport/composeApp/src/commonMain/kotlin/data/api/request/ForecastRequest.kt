package data.api.request

import data.model.CurrentLocation

sealed interface ForecastRequest {
    data class City(
        val city: data.model.City,
        val days: Int,
        val lang: String = "ja",
        val tp: Int = 24
    ) : ForecastRequest

    data class Location(
        val location: CurrentLocation,
        val days: Int,
        val lang: String = "ja",
        val tp: Int = 24
    ) : ForecastRequest
}

fun ForecastRequest.toQueryStrings(): String = when (this) {
    is ForecastRequest.City -> "q=$city&days=$days&lang=$lang&tp=$tp"
    is ForecastRequest.Location -> "q=${location.latitude},${location.longitude}&days=$days&lang=$lang&tp=$tp"
}
