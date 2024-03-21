package data.api

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val apiModule = module {
    factoryOf(::FreeWeatherApiClientFactory)
    singleOf(::FreeWeatherApi)
}