import org.koin.core.annotation.Single

@Single
class SampleComponent {
    fun greeting(): String {
        return "Hello"
    }
}