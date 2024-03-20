import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ui.japan.JapanViewModel

val appModule = module {
    singleOf(::SampleComponent) bind SampleComponent::class
    viewModelDefinition { CalculatorViewModel() }
    viewModelDefinition { JapanViewModel(get()) }
}