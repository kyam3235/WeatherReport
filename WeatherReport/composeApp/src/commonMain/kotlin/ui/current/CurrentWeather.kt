package ui.current

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import data.model.ForecastWeather
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import io.ktor.util.date.GMTDate
import io.ktor.util.date.plus
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import weatherreport.composeapp.generated.resources.Res
import weatherreport.composeapp.generated.resources.current_label_last_updated
import weatherreport.composeapp.generated.resources.current_label_temperature
import kotlin.time.Duration.Companion.hours

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CurrentWeather(
    modifier: Modifier = Modifier,
    forecastWeather: ForecastWeather
) {
    val date = GMTDate(forecastWeather.currentInfo.lastUpdatedEpoch * 1000)
        .plus(duration = 9.hours)
    Column(modifier = modifier) {
        Text(
            text = forecastWeather.locationInfo.name,
            style = MaterialTheme.typography.h4,
            color = Color.DarkGray
        )
        Text(
            text = stringResource(
                Res.string.current_label_last_updated,
                date.month.ordinal + 1,
                date.dayOfMonth,
                date.hours,
                date.minutes.toString().padStart(2, '0')
            ),
            style = MaterialTheme.typography.body1,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.size(size = 4.dp))
        Card(
            elevation = 4.dp,
        ) {
            Row(
                verticalAlignment = CenterVertically
            ) {
                KamelImage(
                    modifier = Modifier.weight(1.0f),
                    resource = asyncPainterResource(data = "https:${forecastWeather.currentInfo.conditionInfo.icon}"),
                    contentDescription = null,
                    onLoading = { progress -> CircularProgressIndicator(progress) }
                )

                Column(
                    modifier = Modifier.weight(1.0f),
                    horizontalAlignment = CenterHorizontally
                ) {
                    Text(
                        text = forecastWeather.currentInfo.conditionInfo.text,
                        style = MaterialTheme.typography.h6,
                        color = Color.DarkGray
                    )
                    Text(
                        text = stringResource(
                            Res.string.current_label_temperature,
                            forecastWeather.currentInfo.temperature
                        ),
                        style = MaterialTheme.typography.h6,
                        color = Color.DarkGray
                    )
                }
            }
        }
    }
}