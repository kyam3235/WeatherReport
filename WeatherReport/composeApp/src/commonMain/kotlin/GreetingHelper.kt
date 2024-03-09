import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GreetingHelper : KoinComponent {
    private val greeting: SampleComponent by inject()
    fun greet(): String = greeting.greeting()
}