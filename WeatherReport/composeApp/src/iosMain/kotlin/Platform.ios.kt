import data.model.CurrentLocation
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCClass
import kotlinx.cinterop.useContents
import objcnames.classes.Protocol
import org.koin.core.definition.Definition
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.CLLocationManagerDelegateProtocol
import platform.darwin.NSUInteger

actual inline fun <reified T : ViewModel> Module.viewModelDefinition(
    qualifier: Qualifier?,
    noinline definition: Definition<T>,
): KoinDefinition<T> = factory(qualifier = qualifier, definition = definition)

@OptIn(ExperimentalForeignApi::class)
actual fun getCurrentLocation(body: (CurrentLocation) -> Unit) {
    // TODO 現在地の取得
    val locationManager = CLLocationManager()
    locationManager.delegate = object : CLLocationManagerDelegateProtocol {
        override fun locationManager(manager: CLLocationManager, didUpdateLocations: List<*>) {
            manager.location?.let {
                body(CurrentLocation(
                    latitude = it.coordinate().useContents { latitude },
                    longitude = it.coordinate().useContents { longitude }
                ))
            }
        }

        override fun description(): String? {
            TODO("Not yet implemented")
        }

        override fun `class`(): ObjCClass? {
            TODO("Not yet implemented")
        }

        override fun conformsToProtocol(aProtocol: Protocol?): Boolean {
            TODO("Not yet implemented")
        }

        override fun hash(): NSUInteger {
            TODO("Not yet implemented")
        }

        override fun isEqual(`object`: Any?): Boolean {
            TODO("Not yet implemented")
        }

        override fun isKindOfClass(aClass: ObjCClass?): Boolean {
            TODO("Not yet implemented")
        }

        override fun isMemberOfClass(aClass: ObjCClass?): Boolean {
            TODO("Not yet implemented")
        }

        override fun isProxy(): Boolean {
            TODO("Not yet implemented")
        }

        override fun performSelector(aSelector: COpaquePointer?): Any? {
            TODO("Not yet implemented")
        }

        override fun performSelector(
            aSelector: COpaquePointer?,
            withObject: Any?,
            _withObject: Any?
        ): Any? {
            TODO("Not yet implemented")
        }

        override fun performSelector(aSelector: COpaquePointer?, withObject: Any?): Any? {
            TODO("Not yet implemented")
        }

        override fun respondsToSelector(aSelector: COpaquePointer?): Boolean {
            TODO("Not yet implemented")
        }

        override fun superclass(): ObjCClass? {
            TODO("Not yet implemented")
        }
    }
    locationManager.requestLocation()
}