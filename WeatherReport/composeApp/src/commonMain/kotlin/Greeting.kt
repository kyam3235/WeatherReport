import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import jp.kyamlab.weatherreport.BuildKonfig.FREE_WEATHER_API_KEY
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

class Greeting {
    @OptIn(ExperimentalSerializationApi::class)
    private val client = HttpClient() {
        install(HttpTimeout) {
            socketTimeoutMillis = 60_000
            requestTimeoutMillis = 60_000
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    co.touchlab.kermit.Logger.d("KtorClient") {
                        message
                    }
                }
            }
        }
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                explicitNulls = false
            })
        }
    }

    suspend fun greet(): String {
        val response =
            client.get("https://api.weatherapi.com/v1/current.json?key=${FREE_WEATHER_API_KEY}&q=Tokyo&aqi=no")
        return response.bodyAsText()
    }
}