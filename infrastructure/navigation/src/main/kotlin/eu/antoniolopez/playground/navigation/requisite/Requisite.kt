package eu.antoniolopez.playground.navigation.requisite

import eu.antoniolopez.playground.navigation.NavigationCommand

interface Requisite {
    val navigationCommand: NavigationCommand

    fun isAccomplished(): Boolean
}
