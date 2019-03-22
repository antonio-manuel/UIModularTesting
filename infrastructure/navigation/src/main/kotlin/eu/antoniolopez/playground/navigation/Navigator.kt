package eu.antoniolopez.playground.navigation

import android.content.Context
import android.support.v4.app.Fragment
import arrow.core.Try
import eu.antoniolopez.playground.navigation.command.Command

typealias NavigationCommand = (schema: String) -> Command

interface Navigator {
    fun navigate(context: Context?, navigationCommand: NavigationCommand): Try<Unit>
    fun navigateForResult(fragment: Fragment, navigationCommand: NavigationCommand, requestCode: Int): Try<Unit>
}

fun NavigationCommand.toUri(context: Context) = this(context.getScheme()).uri

internal fun Context.getScheme(): String {
    return getString(R.string.scheme)
}
