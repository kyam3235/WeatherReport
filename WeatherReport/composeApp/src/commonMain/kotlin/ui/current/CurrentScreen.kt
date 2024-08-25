package ui.current

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
                modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min),
                forecastWeather = forecast
            )

            ForecastList(
                modifier = Modifier.fillMaxWidth(),
                forecastInfos = forecast.forecastInfos
            )
        }
    }
}