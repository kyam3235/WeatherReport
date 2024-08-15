package ui.current

import data.model.CurrentLocation
import data.model.TwoDaysWeather
import data.repository.WeatherRepository
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.util.date.GMTDate
import io.ktor.util.date.Month
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
        val weather = weatherRepository.getCurrentWeather(currentLocation)
        val date = GMTDate(weather.dateEpoch * 1000)
        reduce {
            state.copy(
                date = "${Month.Companion.from(date.month.value).ordinal + 1}月${date.dayOfMonth}日",
                weather = weather
            )
        }
    }
}

data class CurrentState(
    val date: String = "",
    val weather: TwoDaysWeather? = null,
)

sealed class CurrentSideEffect {
    // Nothing
}