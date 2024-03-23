package ui.japan

import data.model.City
import data.model.TwoDaysWeather
import data.repository.WeatherRepository
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

class JapanViewModel(
    private val weatherRepository: WeatherRepository
) :
    ViewModel(),
    ContainerHost<JapanState, JapanSideEffect> {
    override val container: Container<JapanState, JapanSideEffect> =
        viewModelScope.container(JapanState())

    init {
        viewModelScope.launch {
            getWeathers()
        }
    }

    private suspend fun getWeathers() = intent {
        val newWeathers = mutableListOf<TwoDaysWeather>()
        newWeathers.add(weatherRepository.getTwoDaysWeather(City.TOKYO))
//        Prefecture.entries.forEach {
//            newWeathers.add(weatherRepository.getTwoDaysWeather(it))
//        }
        reduce {
            state.copy(
                weathers = newWeathers
            )
        }
    }
}

data class JapanState(
    val weathers: List<TwoDaysWeather> = listOf()
)

sealed class JapanSideEffect {
    // Nothing
}