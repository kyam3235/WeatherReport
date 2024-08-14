package ui.current

import data.model.CurrentLocation
import data.repository.WeatherRepository
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

class CurrentViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel(),
    ContainerHost<CurrentState, CurrentSideEffect> {
    override val container: Container<CurrentState, CurrentSideEffect> =
        viewModelScope.container(CurrentState())

    fun updateCurrentLocation(currentLocation: CurrentLocation) = intent {
        reduce {
            state.copy(
                currentLocation = currentLocation
            )
        }
    }
}

data class CurrentState(
    val currentLocation: CurrentLocation = CurrentLocation(
        latitude = 0.0,
        longitude = 0.0
    )
)

sealed class CurrentSideEffect {
    // Nothing
}