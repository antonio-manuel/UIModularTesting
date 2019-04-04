package eu.antoniolopez.playground.di

import eu.antoniolopez.playground.core.di.coreComponent
import eu.antoniolopez.playground.navigation.di.navigationModule
import org.kodein.di.conf.ConfigurableKodein

val appComponent = ConfigurableKodein(mutable = true).apply {
    this.addExtend(coreComponent)
    this.addImport(navigationModule)
    this.addImport(appModule)
}
