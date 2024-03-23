package ui.japan

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import data.model.City
import data.model.OneDayWeather
import data.model.TwoDaysWeather
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.utils.Colors
import ui.utils.VerticalDivider

@Composable
fun TwoDaysCard(
    twoDaysWeather: TwoDaysWeather
) {
    Card(
        border = BorderStroke(
            width = 1.dp,
            color = Colors.DeepSkyBlue
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp).height(IntrinsicSize.Min)
        ) {
            Text(
                text = twoDaysWeather.city.label,
                style = MaterialTheme.typography.subtitle1
            )
            Row {
                WeatherInfo(
                    label = "今日",
                    oneDayWeather = twoDaysWeather.today
                )
                Spacer(modifier = Modifier.size(8.dp))
                VerticalDivider(color = Color.Gray)
                Spacer(modifier = Modifier.size(8.dp))
                WeatherInfo(
                    label = "明日",
                    oneDayWeather = twoDaysWeather.tomorrow
                )
            }
        }
    }
}

@Composable
private fun WeatherInfo(
    label: String,
    oneDayWeather: OneDayWeather
) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.subtitle2
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            KamelImage(
                modifier = Modifier.size(48.dp),
                resource = asyncPainterResource(data = "https:${oneDayWeather.iconUrl}"),
                contentDescription = null,
                onLoading = { progress -> CircularProgressIndicator(progress) }
            )
            Spacer(modifier = Modifier.size(4.dp))
            Column {
                Row {
                    Text(
                        text = "${oneDayWeather.maxTemperatureCelsius}℃",
                        style = MaterialTheme.typography.body2,
                        color = Color.Red
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = "/",
                        style = MaterialTheme.typography.body2,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = "${oneDayWeather.minTemperatureCelsius}℃",
                        style = MaterialTheme.typography.body2,
                        color = Color.Blue
                    )
                }
                Text(
                    text = "${oneDayWeather.dailyChanceOfRain}%",
                    style = MaterialTheme.typography.body2,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
@Preview
fun TwoDaysCardPreview() {
    TwoDaysCard(
        TwoDaysWeather(
            city = City.TOKYO,
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
