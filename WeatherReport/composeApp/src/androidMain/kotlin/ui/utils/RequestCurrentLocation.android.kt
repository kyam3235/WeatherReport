package ui.utils

import android.annotation.SuppressLint
import android.location.Location
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import applicationContext
import co.touchlab.kermit.Logger
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationServices
import data.model.CurrentLocation

@OptIn(ExperimentalPermissionsApi::class)
@Composable
actual fun RequestCurrentLocation(
    modifier: Modifier,
    body: (CurrentLocation) -> Unit
) {
    val locationPermissionState = rememberMultiplePermissionsState(
        listOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )
    if (locationPermissionState.allPermissionsGranted) {
        getCurrentLocation { location ->
            body(location)
        }
    } else {
        LaunchedEffect(Unit) {
            locationPermissionState.launchMultiplePermissionRequest()
        }
    }
}

@SuppressLint("MissingPermission")
private fun getCurrentLocation(body: (CurrentLocation) -> Unit) {
    LocationServices.getFusedLocationProviderClient(applicationContext).lastLocation
        .addOnSuccessListener { location: Location? ->
            location?.let {
                Logger.d("現在地 - lat: ${it.latitude}, lon: ${it.longitude}")
                body(CurrentLocation(it.latitude, it.longitude))
            }
        }
}
