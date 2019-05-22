package eu.antoniolopez.playground.navigation

import android.app.Dialog
import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import arrow.core.Try
import eu.antoniolopez.playground.navigation.command.Command
import eu.antoniolopez.playground.navigation.extension.resolveIntent

class NavigatorImpl : Navigator {
    private var pendingNavigation: Command? = null

    override fun navigate(context: Context?, navigationCommand: NavigationCommand) =
        Try {
            context?.let {
                val command = getCommand(context, navigationCommand)
                stackOrExecute(context, command) { intent ->
                    context.startActivity(intent)
                }

            } ?: throw IllegalStateException()
        }

    override fun navigateForResult(fragment: Fragment, navigationCommand: NavigationCommand, requestCode: Int) =
        Try {
            fragment.activity?.let { context ->
                val command = getCommand(context, navigationCommand)
                stackOrExecute(context, command) { intent ->
                    fragment.startActivityForResult(intent, requestCode)
                }
            } ?: throw IllegalStateException()
        }

    private fun clearPendingNavigation() {
        pendingNavigation = null
    }

    private fun stackOrExecute(context: Context, command: Command, executionBlock: (Intent) -> Unit) {
        verifyPreRequisites(context, command,
            onBlock = { command ->
                if (pendingNavigation == null) {
                    pendingNavigation = command
                }
            },
            onSuccess = { command ->
                if (!command.skipPendingCommand) {
                    clearPendingNavigation()
                }
                val intent = command.toIntent()
                whenSupportIntent(context, intent) {
                    executionBlock(intent)
                }
            }
        )
    }

    private fun getCommand(context: Context, navigationCommand: NavigationCommand): Command {
        val currentCommand = navigationCommand(context.getScheme())
        return if (currentCommand.skipPendingCommand) {
            currentCommand
        } else {
            pendingNavigation ?: currentCommand
        }
    }

    private fun verifyPreRequisites(
        context: Context,
        command: Command,
        onSuccess: (Command) -> Unit,
        onBlock: (Command) -> Unit
    ) {
        command.requisites.forEach { requisite ->
            if (!requisite.isAccomplished()) {
                val intent = requisite.navigationCommand(context.getScheme()).toIntent()
                whenSupportIntent(context, intent) {
                    context.startActivity(intent)
                }
                onBlock(command)
                return
            }
        }
        onSuccess(command)
    }

    private fun whenSupportIntent(context: Context, intent: Intent, block: () -> Unit) {
        if (context.resolveIntent(intent)) {
            block()
        } else {
            showNavigationNotFoundDialog(context)
        }
    }

    private fun showNavigationNotFoundDialog(context: Context) = Dialog(context).apply {
        setContentView(R.layout.dialog_navigation_not_available)
    }.show()
}
