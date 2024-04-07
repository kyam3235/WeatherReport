import data.model.PermissionState
import platform.CoreLocation.CLLocationManager

internal class LocationServicePermissionDelegate : PermissionDelegate {
    private val locationManager = CLLocationManager()

    override fun getPermissionState(): PermissionState {
        return if (locationManager.locationServicesEnabled())
            PermissionState.GRANTED else PermissionState.DENIED
    }

    override suspend fun providePermission() {
        openSettingPage()
    }

    override fun openSettingPage() {
        openNSUrl("App-Prefs:Privacy&path=LOCATION")
    }
}