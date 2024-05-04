import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.kCLAuthorizationStatusAuthorizedAlways
import platform.CoreLocation.kCLAuthorizationStatusAuthorizedWhenInUse
import platform.CoreLocation.kCLAuthorizationStatusRestricted

@Composable
actual fun LocationView(
    modifier: Modifier
) {
    val locationManager = CLLocationManager()
    LaunchedEffect(Unit) {
        when (locationManager.authorizationStatus()) {
            kCLAuthorizationStatusAuthorizedAlways,
            kCLAuthorizationStatusAuthorizedWhenInUse,
            kCLAuthorizationStatusRestricted -> {
                // 何もしない
            }

            else -> {
                locationManager.requestWhenInUseAuthorization()
            }
        }
    }
}