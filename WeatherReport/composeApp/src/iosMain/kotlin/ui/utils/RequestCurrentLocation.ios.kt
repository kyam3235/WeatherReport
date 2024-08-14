package ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import co.touchlab.kermit.Logger
import data.model.CurrentLocation
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.CLLocationManagerDelegateProtocol
import platform.CoreLocation.kCLAuthorizationStatusAuthorizedAlways
import platform.CoreLocation.kCLAuthorizationStatusAuthorizedWhenInUse
import platform.CoreLocation.kCLAuthorizationStatusRestricted
import platform.Foundation.NSError
import platform.darwin.NSObject

val locationManager by lazy {
    CLLocationManager()
}

@Composable
actual fun RequestCurrentLocation(
    modifier: Modifier,
    body: (CurrentLocation) -> Unit
) {
    LaunchedEffect(Unit) {
        when (locationManager.authorizationStatus()) {
            kCLAuthorizationStatusAuthorizedAlways,
            kCLAuthorizationStatusAuthorizedWhenInUse,
            kCLAuthorizationStatusRestricted -> {
                getCurrentLocation { location ->
                    body(location)
                }
            }

            else -> {
                locationManager.requestWhenInUseAuthorization()
            }
        }
    }
}

@OptIn(ExperimentalForeignApi::class)
private fun getCurrentLocation(body: (CurrentLocation) -> Unit) {
    locationManager.delegate = object : NSObject(), CLLocationManagerDelegateProtocol {
        override fun locationManager(manager: CLLocationManager, didUpdateLocations: List<*>) {
            manager.location?.let {
                val latitude = it.coordinate().useContents { latitude }
                val longitude = it.coordinate().useContents { longitude }
                Logger.d("現在地 - lat: $latitude, lon: $longitude")
                body(
                    CurrentLocation(
                        latitude = latitude,
                        longitude = longitude
                    )
                )
            }
        }

        override fun locationManager(manager: CLLocationManager, didFailWithError: NSError) {
            Logger.e("$didFailWithError")
        }

        override fun locationManagerDidChangeAuthorization(manager: CLLocationManager) {
            Logger.d("locationManagerDidChangeAuthorization")
        }
    }
    locationManager.requestLocation()
}
