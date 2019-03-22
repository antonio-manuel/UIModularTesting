package eu.antoniolopez.playground.core.di

import org.kodein.di.Kodein

val coreComponent = Kodein {
    import(coreModule)
}
