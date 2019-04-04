package eu.antoniolopez.playground.di

import eu.antoniolopez.playground.presenter.MainPresenter
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

val appModule = Kodein.Module(name = "appModule") {
   bind<MainPresenter>() with provider { MainPresenter() }
}
