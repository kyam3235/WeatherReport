package ui.current

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import ui.utils.RequestCurrentLocation

@Composable
fun CurrentScreen(viewModel: CurrentViewModel) {
    val state = viewModel.container.stateFlow.collectAsState().value

    RequestCurrentLocation(
        modifier = Modifier,
        body = { location ->
            viewModel.onUpdateCurrentLocation(currentLocation = location)
        }
    )

    Column {
        state.forecastWeather?.let { forecast ->
            CurrentWeather(
                modifier = Modifier.fillMaxWidth().weight(1f),
                forecastWeather = forecast
            )

            ForecastList(
                modifier = Modifier.fillMaxWidth().weight(1f),
                forecastInfos = forecast.forecastInfos
            )
        }
    }
}