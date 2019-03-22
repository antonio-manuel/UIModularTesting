package eu.antoniolopez.playground.navigation.command

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import eu.antoniolopez.playground.navigation.requisite.Requisite

data class Command(
    val uri: Uri,
    val flags: Int? = null,
    val skipPendingCommand: Boolean = false,
    val requisites: List<Requisite> = listOf(),
    val packageName: String? = null
) {
    fun toIntent(): Intent {
        val intent = Intent(ACTION_VIEW, uri)
        packageName?.let { intent.`package` = it }
        flags?.let { intent.flags = it }
        return intent
    }
}
