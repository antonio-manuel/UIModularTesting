package eu.antoniolopez.playground.core.di

import android.app.Application
import android.content.Context
import eu.antoniolopez.playground.exceptions.ContextException
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

val coreModule = Kodein.Module(name = "CORE_MODULE") {
    bind<Context>() with singleton {
        APPLICATION_CONTEXT ?: throw ContextException()
    }
}

private var APPLICATION_CONTEXT: Context? = null

fun Application.setApplicationContext() {
    APPLICATION_CONTEXT = this
}
