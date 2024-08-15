package ui.current

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import data.model.ForecastWeather
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import io.ktor.util.date.GMTDate

@Composable
fun CurrentWeather(
    modifier: Modifier = Modifier,
    forecastWeather: ForecastWeather
) {
    val date = GMTDate(forecastWeather.currentInfo.lastUpdatedEpoch * 1000)
    Column(modifier = modifier) {
        Text(
            text = "現在の天気(更新日時:${date.month.ordinal + 1}/${date.dayOfMonth} ${date.hours}:${date.minutes})",
            style = MaterialTheme.typography.h6
        )
        Text(
            text = forecastWeather.locationInfo.name,
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.size(size = 4.dp))
        Row(
            verticalAlignment = CenterVertically
        ) {
            KamelImage(
                modifier = Modifier.weight(1.0f),
                resource = asyncPainterResource(data = "https:${forecastWeather.currentInfo.conditionInfo.icon}"),
                contentDescription = null,
                onLoading = { progress -> CircularProgressIndicator(progress) }
            )

            Column(modifier = Modifier.weight(1.0f)) {
                Text(
                    text = forecastWeather.currentInfo.conditionInfo.text,
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = "気温 ${forecastWeather.currentInfo.temperature}℃",
                    style = MaterialTheme.typography.body1,
                    color = Color.Red
                )
            }
        }
    }
}