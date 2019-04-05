package eu.antoniolopez.playground.feature.helloworld.di

import eu.antoniolopez.playground.navigation.di.navigationModule
import org.kodein.di.conf.ConfigurableKodein

val featureComponent = ConfigurableKodein(mutable = true).apply {
    this.addImport(featureModule)
    this.addImport(navigationModule)
}
