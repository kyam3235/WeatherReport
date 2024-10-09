package ui.current

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import ui.utils.RequestCurrentLocation
import weatherreport.composeapp.generated.resources.Res
import weatherreport.composeapp.generated.resources.current_forecast_list_title

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CurrentScreen(viewModel: CurrentViewModel) {
    val state = viewModel.container.stateFlow.collectAsState().value

    RequestCurrentLocation(
        modifier = Modifier,
        body = { location ->
            viewModel.onUpdateCurrentLocation(currentLocation = location)
        }
    )

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        state.forecastWeather?.let { forecast ->
            CurrentWeather(
                modifier = Modifier.fillMaxWidth().weight(3f),
                forecastWeather = forecast,
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = stringResource(Res.string.current_forecast_list_title),
                style = MaterialTheme.typography.h5,
                color = Color.DarkGray,
            )
            ForecastList(
                modifier = Modifier.fillMaxWidth().weight(7f).padding(top = 4.dp, bottom = 24.dp),
                forecastInfos = forecast.forecastInfos,
            )
        }
    }
}