package ui.japan

import data.model.City
import data.model.TwoDaysWeather
import data.repository.WeatherRepository
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.util.date.GMTDate
import io.ktor.util.date.Month
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
        newWeathers.add(weatherRepository.getTwoDaysWeather(City.SAPPORO))
        newWeathers.add(weatherRepository.getTwoDaysWeather(City.TOKYO))
        newWeathers.add(weatherRepository.getTwoDaysWeather(City.NAGOYA))
        newWeathers.add(weatherRepository.getTwoDaysWeather(City.OSAKA))
        newWeathers.add(weatherRepository.getTwoDaysWeather(City.FUKUOKA))
        val date = GMTDate(newWeathers[0].dateEpoch * 1000)
        reduce {
            state.copy(
                date = "${Month.Companion.from(date.month.value).ordinal + 1}月${date.dayOfMonth}日",
                weathers = newWeathers
            )
        }
    }
}

data class JapanState(
    val date: String = "",
    val weathers: List<TwoDaysWeather> = listOf()
)

sealed class JapanSideEffect {
    // Nothing
}