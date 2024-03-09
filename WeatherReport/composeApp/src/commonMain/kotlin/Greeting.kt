import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import jp.kyamlab.weatherreport.BuildKonfig.FREE_WEATHER_API_KEY

class Greeting {
    private val client = HttpClient()

    suspend fun greet(): String {
        val response = client.get("https://api.weatherapi.com/v1/current.json?key=${FREE_WEATHER_API_KEY}&q=Tokyo&aqi=no")
        return response.bodyAsText()
    }
}