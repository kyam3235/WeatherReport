import dev.icerock.moko.mvvm.viewmodel.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

class CalculatorViewModel :
    ViewModel(),
    ContainerHost<CalculatorState, CalculatorSideEffect> {
    override val container: Container<CalculatorState, CalculatorSideEffect> =
        viewModelScope.container(CalculatorState())

    fun add(number: Int) = intent {
        reduce {
            state.copy(total = state.total + number)
        }
//        postSideEffect(CalculatorSideEffect.Toast("Adding $number to ${state.total}!"))
//
//        reduce {
//            state.copy(total = state.total + number)
//        }
    }
}

data class CalculatorState(
    val total: Int = 0
)

sealed class CalculatorSideEffect {
    data class Toast(val text: String) : CalculatorSideEffect()
}
