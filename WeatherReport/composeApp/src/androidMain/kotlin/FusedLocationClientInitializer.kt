import android.content.Context
import androidx.startup.Initializer

lateinit var applicationContext: Context

object KLocationContext

class FusedLocationClientInitializer : Initializer<KLocationContext> {
    override fun create(context: Context): KLocationContext {
        applicationContext = context.applicationContext
        return KLocationContext
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}