package ui.current

import data.model.CurrentLocation
import data.model.ForecastWeather
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

    fun onUpdateCurrentLocation(currentLocation: CurrentLocation) = intent {
        val forecastWeather = weatherRepository.getCurrentWeather(currentLocation)
        reduce {
            state.copy(
                forecastWeather = forecastWeather
            )
        }
    }
}

data class CurrentState(
    val forecastWeather: ForecastWeather? = null,
)

sealed class CurrentSideEffect {
    // Nothing
}