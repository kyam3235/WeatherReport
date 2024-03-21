package ui.japan

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

@Composable
fun JapanScreen(viewModel: JapanViewModel) {
    val state = viewModel.container.stateFlow.collectAsState().value

    LazyColumn {
        items(state.weathers.size) { index ->
            TwoDaysCard(weather = state.weathers[index])
//            Row {
//                Text(text = state.weathers[index].prefecture.name)
//                Spacer(modifier = Modifier.size(8.dp))
//                Text(text = state.weathers[index].today.text)
//                Spacer(modifier = Modifier.size(8.dp))
//                Text(text = state.weathers[index].tomorrow.text)
//            }
        }
    }
}