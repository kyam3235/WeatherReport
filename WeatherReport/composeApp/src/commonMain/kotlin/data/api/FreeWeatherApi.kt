package data.api

import data.api.request.ForecastRequest
import data.api.request.toQueryStrings
import data.api.response.ForecastResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import jp.kyamlab.weatherreport.BuildKonfig
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FreeWeatherApi : KoinComponent {
    private val factory: FreeWeatherApiClientFactory by inject()
    private val client by lazy {
        factory.client
    }

    // FIXME: モデルクラスを返すよう変更
    suspend fun current(area: String): String {
        val response =
            client.get("$BASE_URL/current.json?key=${BuildKonfig.FREE_WEATHER_API_KEY}&q=$area&aqi=no")
        return response.bodyAsText()
    }

    /**
     * Forecast weather API method returns, depending upon your price plan level, upto next 14 day weather forecast and weather alert as json or xml. The data is returned as a Forecast Object.
     * Forecast object contains astronomy data, day weather forecast and hourly interval weather information for a given city.
     */
    suspend fun forecast(
        request: ForecastRequest
    ): ForecastResponse {
        val response =
            client.get("$BASE_URL/forecast.json?key=${BuildKonfig.FREE_WEATHER_API_KEY}&${request.toQueryStrings()}")
        return response.body()
    }

    companion object {
        private const val BASE_URL =
            "https://api.weatherapi.com/v1"
    }
}