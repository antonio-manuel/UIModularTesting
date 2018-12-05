package eu.antoniolopez.base.di

import eu.antoniolopez.base.presenter.ButtonPresenter
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

internal val featureModule = Kodein.Module(name = "FEATURE_MODULE") {
    bind<ButtonPresenter>() with provider { ButtonPresenter() }
}
