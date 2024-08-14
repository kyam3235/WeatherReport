import org.koin.dsl.module
import ui.current.CurrentViewModel
import ui.japan.JapanViewModel

val appModule = module {
    viewModelDefinition { CalculatorViewModel() }
    viewModelDefinition { JapanViewModel(get()) }
    viewModelDefinition { CurrentViewModel(get()) }
}