package ui.japan

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun JapanScreen(viewModel: JapanViewModel) {
    val state = viewModel.container.stateFlow.collectAsState().value

    LazyColumn {
        items(state.weathers.size) { index ->
            Row {
                Text(text = state.weathers[index].prefecture.name)
                Spacer(modifier = Modifier.size(8.dp))
                Text(text = state.weathers[index].today.text)
                Spacer(modifier = Modifier.size(8.dp))
                Text(text = state.weathers[index].tomorrow.text)
            }
        }
    }
}