package ui.japan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.model.OneDayWeather
import data.model.Prefecture
import data.model.TwoDaysWeather
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TwoDaysCard(
    weather: TwoDaysWeather
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Text(text = weather.prefecture.name)
            Row {
                KamelImage(
                    modifier = Modifier.size(64.dp),
                    resource = asyncPainterResource(data = "https:${weather.today.iconUrl}"),
                    contentDescription = null,
                    onLoading = { progress -> CircularProgressIndicator(progress) }
                )
                Spacer(modifier = Modifier.size(16.dp))
                Column {
                    Text(text = weather.today.text)
                    Spacer(modifier = Modifier.size(8.dp))
                    Row {
                        Text(text = "${weather.today.maxTemperatureCelsius}℃")
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(text = "${weather.today.minTemperatureCelsius}℃")
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun TwoDaysCardPreview() {
    TwoDaysCard(
        TwoDaysWeather(
            prefecture = Prefecture.TOKYO,
            today = OneDayWeather(
                iconUrl = "",
                text = "晴れ",
                maxTemperatureCelsius = 20,
                minTemperatureCelsius = 10,
                dailyChanceOfRain = 10
            ),
            tomorrow = OneDayWeather(
                iconUrl = "",
                text = "晴れ",
                maxTemperatureCelsius = 20,
                minTemperatureCelsius = 10,
                dailyChanceOfRain = 10
            )
        )
    )
}
