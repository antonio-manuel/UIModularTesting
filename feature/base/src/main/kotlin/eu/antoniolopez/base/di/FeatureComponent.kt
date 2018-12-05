package eu.antoniolopez.base.di

import org.kodein.di.Kodein

var featureComponent = Kodein {
    import(featureModule)
}
