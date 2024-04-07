package jp.kyamlab.weatherreport

import CannotOpenSettingsException
import PermissionDelegate
import android.content.Context
import android.location.LocationManager
import android.provider.Settings
import data.model.Permission
import data.model.PermissionState
import openPage

internal class LocationServicePermissionDelegate(
    private val context: Context,
    private val locationManager: LocationManager
) : PermissionDelegate {
    override fun getPermissionState(): PermissionState {
        val granted = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        return if (granted)
            PermissionState.GRANTED else PermissionState.DENIED
    }

    override suspend fun providePermission() {
        openSettingPage()
    }

    override fun openSettingPage() {
        context.openPage(
            action = Settings.ACTION_LOCATION_SOURCE_SETTINGS,
            onError = { throw CannotOpenSettingsException(Permission.LOCATION_SERVICE_ON.name) }
        )
    }
}