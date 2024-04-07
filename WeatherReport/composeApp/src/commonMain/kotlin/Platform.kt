import dev.icerock.moko.mvvm.viewmodel.ViewModel
import org.koin.core.definition.Definition
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.module
import service.PermissionsService
import service.PermissionsServiceImpl

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect inline fun <reified T : ViewModel> Module.viewModelDefinition(
    qualifier: Qualifier? = null,
    noinline definition: Definition<T>
): KoinDefinition<T>

internal expect fun platformModule(): Module

val permissionsModule: Module = module {
    includes(platformModule())

    single<PermissionsService> {
        PermissionsServiceImpl()
    }
}