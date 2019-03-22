package eu.antoniolopez.playground.navigation.di

import eu.antoniolopez.playground.navigation.Navigator
import eu.antoniolopez.playground.navigation.NavigatorImpl
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

val navigationModule = Kodein.Module(name = "NAVIGATION_MODULE") {
    bind<Navigator>() with singleton { NavigatorImpl() }
}
