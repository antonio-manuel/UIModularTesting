package eu.antoniolopez.playground.navigation.command.goodbyeworld

import android.net.Uri
import eu.antoniolopez.playground.navigation.NavigationCommand
import eu.antoniolopez.playground.navigation.command.Command

val goodbyeWorldNavigationCommand: () -> NavigationCommand = {
    val command: NavigationCommand = { scheme ->
        val uri = Uri.Builder()
            .scheme(scheme)
            .authority("goodbyeworld")
            .build()
        Command(uri = uri)
    }
    command
}
