import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    singleOf(::SampleComponent) bind SampleComponent::class
    viewModelDefinition { CalculatorViewModel() }
}