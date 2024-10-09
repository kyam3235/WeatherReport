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
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import weatherreport.composeapp.generated.resources.Res
import weatherreport.composeapp.generated.resources.current_forecast_day_chance_of_rain
import weatherreport.composeapp.generated.resources.current_forecast_day_max_temperature
import weatherreport.composeapp.generated.resources.current_forecast_day_min_temperature
import kotlin.time.Duration.Companion.hours

@OptIn(ExperimentalResourceApi::class)
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
                text = stringResource(
                    Res.string.current_forecast_day_max_temperature,
                    forecastInfo.dayInfo.maxTemperature
                ),
                style = MaterialTheme.typography.body2,
                color = Color.Red
            )
            Text(
                text = stringResource(
                    Res.string.current_forecast_day_min_temperature,
                    forecastInfo.dayInfo.minTemperature
                ),
                style = MaterialTheme.typography.body2,
                color = Color.Blue
            )
            Text(
                text = stringResource(
                    Res.string.current_forecast_day_chance_of_rain,
                    forecastInfo.dayInfo.chanceOfRain
                ),
                style = MaterialTheme.typography.body2,
                color = Color.Cyan
            )
        }
    }
}