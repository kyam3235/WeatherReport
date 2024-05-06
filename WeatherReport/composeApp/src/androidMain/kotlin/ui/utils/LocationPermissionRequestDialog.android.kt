package ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
actual fun LocationPermissionRequestDialog(
    modifier: Modifier
) {
    val locationPermissionState = rememberMultiplePermissionsState(
        listOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )
    if (locationPermissionState.allPermissionsGranted) {
        // 何もしない
    } else {
        LaunchedEffect(Unit) {
            locationPermissionState.launchMultiplePermissionRequest()
        }
    }
}