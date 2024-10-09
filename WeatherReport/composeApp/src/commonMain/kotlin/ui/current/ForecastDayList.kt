package ui.current

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.model.ForecastInfo

@Composable
fun ForecastList(
    modifier: Modifier = Modifier,
    forecastInfos: List<ForecastInfo>
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(space = 8.dp),
    ) {
        items(
            count = forecastInfos.size,
            itemContent = { index ->
                ForecastDayItem(
                    forecastInfo = forecastInfos[index]
                )
            }
        )
    }
}