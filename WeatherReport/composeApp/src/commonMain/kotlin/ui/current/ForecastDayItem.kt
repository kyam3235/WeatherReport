package ui.current

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import data.model.ForecastInfo
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import io.ktor.util.date.GMTDate
import io.ktor.util.date.plus
import kotlin.time.Duration.Companion.hours

@Composable
fun ForecastDayItem(
    modifier: Modifier = Modifier,
    forecastInfo: ForecastInfo
) {
    val date = GMTDate(forecastInfo.dateEpoch * 1000).plus(duration = 9.hours)
    Row(
        modifier = modifier,
        verticalAlignment = CenterVertically
    ) {
        Text(
            modifier = Modifier.width(64.dp),
            text = "${date.month.ordinal + 1}/${date.dayOfMonth}",
            style = MaterialTheme.typography.body1
        )
        KamelImage(
            modifier = Modifier.fillMaxHeight().weight(1.0f),
            resource = asyncPainterResource(data = "https:${forecastInfo.dayInfo.conditionInfo.icon}"),
            contentDescription = null,
            onLoading = { progress -> CircularProgressIndicator(progress) }
        )
        Column(
            modifier = Modifier.weight(1.0f)
        ) {
            Text(
                text = "最高気温 ${forecastInfo.dayInfo.maxTemperature}℃",
                style = MaterialTheme.typography.body2,
                color = Color.Red
            )
            Text(
                text = "最低気温 ${forecastInfo.dayInfo.minTemperature}℃",
                style = MaterialTheme.typography.body2,
                color = Color.Blue
            )
            Text(
                text = "降水確率 ${forecastInfo.dayInfo.chanceOfRain}%",
                style = MaterialTheme.typography.body2,
                color = Color.Cyan
            )
        }
    }
}