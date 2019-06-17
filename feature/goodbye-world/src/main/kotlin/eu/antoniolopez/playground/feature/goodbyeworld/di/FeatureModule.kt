package eu.antoniolopez.playground.feature.goodbyeworld.di

import eu.antoniolopez.playground.feature.goodbyeworld.presenter.GoodbyeActionPresenter
import eu.antoniolopez.playground.feature.goodbyeworld.presenter.GoodbyeNavigationPresenter
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

internal val featureModule = Kodein.Module(name = "goodbyeWorldModule") {
    bind<GoodbyeActionPresenter>() with provider { GoodbyeActionPresenter() }
    bind<GoodbyeNavigationPresenter>() with provider { GoodbyeNavigationPresenter() }
}
