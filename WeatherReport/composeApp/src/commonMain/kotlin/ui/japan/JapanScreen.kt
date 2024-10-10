package ui.japan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun JapanScreen(viewModel: JapanViewModel) {
    val state = viewModel.container.stateFlow.collectAsState().value

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        columns = GridCells.Fixed(count = 2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(items = state.weathers) { weather ->
            TwoDaysCard(
                twoDaysWeather = weather
            )
        }
    }
}