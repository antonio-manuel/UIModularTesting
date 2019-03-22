package eu.antoniolopez.playground.view

import android.app.Activity
import eu.antoniolopez.playground.core.view.BaseActivity

/**
 * An [Activity] that gets a text string from the user and displays it back when the user
 * clicks on one of the two buttons. The first one shows it in the same activity and the second
 * one opens another activity and displays the message.
 */
class MainActivity : BaseActivity() {
    override fun onRequestFragment() = MainFragment.newInstance()
}
