package data.api.request

import data.model.Prefecture

data class ForecastRequest(
    val prefecture: Prefecture,
    val days: Int,
    val lang: String = "ja",
    val tp: Int = 24
)

fun ForecastRequest.toQueryStrings(): String = "q=$prefecture&days=$days&lang=$lang&tp=$tp"
