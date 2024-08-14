package ui.current

import androidx.compose.material.Text
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
            viewModel.updateCurrentLocation(currentLocation = location)
        }
    )

    Text(text = "lat: ${state.currentLocation.latitude}, lon: ${state.currentLocation.longitude}")
}