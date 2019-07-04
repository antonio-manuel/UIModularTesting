package eu.antoniolopez.playground.navigation.command.helloworld

import android.net.Uri
import eu.antoniolopez.playground.navigation.NavigationCommand
import eu.antoniolopez.playground.navigation.command.Command

val helloWorldNavigationCommand: () -> NavigationCommand = {
    val command: NavigationCommand = { scheme ->
        val uri = Uri.Builder()
            .scheme(scheme)
            .authority("helloworld")
            .build()
        Command(uri = uri)
    }
    command
}
