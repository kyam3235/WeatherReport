package ui.current

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

@Composable
fun CurrentScreen(viewModel: CurrentViewModel) {
    val state = viewModel.container.stateFlow.collectAsState().value
}