package ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import data.model.CurrentLocation

@Composable
expect fun RequestCurrentLocation(
    modifier: Modifier,
    body: (CurrentLocation) -> Unit
)
