import org.koin.dsl.module
import ui.japan.JapanViewModel

val appModule = module {
    viewModelDefinition { CalculatorViewModel() }
    viewModelDefinition { JapanViewModel(get()) }
}