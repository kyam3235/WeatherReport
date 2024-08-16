package ui.current

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import data.model.ForecastInfo

@Composable
fun ForecastList(
    modifier: Modifier = Modifier,
    forecastInfos: List<ForecastInfo>
) {
    LazyColumn(
        modifier = modifier
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