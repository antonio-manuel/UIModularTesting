package eu.antoniolopez.playground.di

import eu.antoniolopez.playground.core.di.coreComponent
import eu.antoniolopez.playground.navigation.di.navigationModule
import org.kodein.di.Kodein

internal val appComponent = Kodein {
    extend(coreComponent)
    import(navigationModule)
    import(appModule)
}
