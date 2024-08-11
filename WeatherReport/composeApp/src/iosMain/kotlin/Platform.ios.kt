import data.model.CurrentLocation
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import org.koin.core.definition.Definition
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.CLLocationManagerDelegateProtocol
import platform.darwin.NSObject

actual inline fun <reified T : ViewModel> Module.viewModelDefinition(
    qualifier: Qualifier?,
    noinline definition: Definition<T>,
): KoinDefinition<T> = factory(qualifier = qualifier, definition = definition)

@OptIn(ExperimentalForeignApi::class)
actual fun getCurrentLocation(body: (CurrentLocation) -> Unit) {
    val locationManager = CLLocationManager()
    locationManager.delegate = object : NSObject(), CLLocationManagerDelegateProtocol {
        override fun locationManager(manager: CLLocationManager, didUpdateLocations: List<*>) {
            manager.location?.let {
                body(CurrentLocation(
                    latitude = it.coordinate().useContents { latitude },
                    longitude = it.coordinate().useContents { longitude }
                ))
            }
        }
    }
    locationManager.requestLocation()
}