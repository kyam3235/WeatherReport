import org.koin.dsl.module
import ui.current.CurrentViewModel
import ui.japan.JapanViewModel

val appModule = module {
    viewModelDefinition { JapanViewModel(get()) }
    viewModelDefinition { CurrentViewModel(get()) }
}