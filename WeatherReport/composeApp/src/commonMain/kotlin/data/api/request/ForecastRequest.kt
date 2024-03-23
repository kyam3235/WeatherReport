package data.api.request

import data.model.City

data class ForecastRequest(
    val city: City,
    val days: Int,
    val lang: String = "ja",
    val tp: Int = 24
)

fun ForecastRequest.toQueryStrings(): String = "q=$city&days=$days&lang=$lang&tp=$tp"
