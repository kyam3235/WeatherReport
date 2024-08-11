import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.LocationServices
import data.model.CurrentLocation
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.definition.Definition
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier

actual inline fun <reified T : ViewModel> Module.viewModelDefinition(
    qualifier: Qualifier?,
    noinline definition: Definition<T>,
): KoinDefinition<T> = viewModel(qualifier = qualifier, definition = definition)

@SuppressLint("MissingPermission")
actual fun getCurrentLocation(body: (CurrentLocation) -> Unit) {
    LocationServices.getFusedLocationProviderClient(applicationContext).lastLocation
        .addOnSuccessListener { location: Location? ->
            location?.let {
                body(CurrentLocation(it.latitude, it.longitude))
            }
        }
}