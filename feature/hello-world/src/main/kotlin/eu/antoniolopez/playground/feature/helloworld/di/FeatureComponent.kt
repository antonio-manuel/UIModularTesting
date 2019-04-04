package eu.antoniolopez.playground.feature.helloworld.di

import org.kodein.di.conf.ConfigurableKodein

val featureComponent = ConfigurableKodein(mutable = true).apply {
    this.addImport(featureModule)
}
