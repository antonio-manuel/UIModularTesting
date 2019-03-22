package eu.antoniolopez.playground.feature.helloworld.di

import org.kodein.di.Kodein

var featureComponent = Kodein {
    import(featureModule)
}
