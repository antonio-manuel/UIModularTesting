package eu.antoniolopez.playground.navigation.extension

import android.content.Context
import android.content.Intent

fun Context.resolveIntent(intent: Intent): Boolean {
    return (intent.resolveActivity(this.packageManager) != null)
}
