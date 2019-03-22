package eu.antoniolopez.playground.navigation.command.helloworld

import android.content.Intent
import android.net.Uri
import eu.antoniolopez.playground.navigation.NavigationCommand
import eu.antoniolopez.playground.navigation.command.Command

val helloWorldNavigationCommand: () -> NavigationCommand = {
    val command: NavigationCommand = { scheme ->
        val uri = Uri.Builder()
            .scheme(scheme)
            .authority("helloworld")
            .build()
        Command(
            uri = uri,
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
        )
    }
    command
}
