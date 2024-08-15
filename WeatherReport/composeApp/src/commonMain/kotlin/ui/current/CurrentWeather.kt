package ui.current

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import data.model.OneDayWeather
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun CurrentWeather(
    modifier: Modifier = Modifier,
    weather: OneDayWeather
) {
    Row(modifier = modifier) {
        KamelImage(
            modifier = Modifier.weight(1.0f),
            resource = asyncPainterResource(data = "https:${weather.iconUrl}"),
            contentDescription = null,
            onLoading = { progress -> CircularProgressIndicator(progress) }
        )

        Column(modifier = Modifier.weight(1.0f)) {
            Text(
                text = "最高気温 ${weather.maxTemperatureCelsius}℃",
                style = MaterialTheme.typography.h5,
                color = Color.Red
            )
            Text(
                text = "最低気温 ${weather.minTemperatureCelsius}℃",
                style = MaterialTheme.typography.h5,
                color = Color.Blue
            )
            Text(
                text = "降水確率 ${weather.dailyChanceOfRain}%",
                style = MaterialTheme.typography.h5,
                color = Color.Gray
            )
        }
    }
}