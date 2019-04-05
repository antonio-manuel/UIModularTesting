package eu.antoniolopez.playground.feature.goodbyeworld.di

import eu.antoniolopez.playground.feature.goodbyeworld.presenter.GoodbyeWorldPresenter
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

internal val featureModule = Kodein.Module(name = "goodbyeWorldModule") {
    bind<GoodbyeWorldPresenter>() with provider { GoodbyeWorldPresenter() }
}
