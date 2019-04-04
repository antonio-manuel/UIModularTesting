package eu.antoniolopez.playground.feature.helloworld.di

import eu.antoniolopez.playground.feature.helloworld.presenter.HelloWorldPresenter
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

internal val featureModule = Kodein.Module(name = "helloWorldModule") {
    bind<HelloWorldPresenter>() with provider { HelloWorldPresenter() }
}
