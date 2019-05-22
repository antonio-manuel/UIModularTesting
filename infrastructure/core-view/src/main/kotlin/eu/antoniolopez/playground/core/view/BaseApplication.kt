package eu.antoniolopez.playground.core.view

import android.app.Application
import android.content.Context
import android.os.StrictMode
import eu.antoniolopez.playground.core.di.setApplicationContext
import eu.antoniolopez.playground.core.logging.CrashReportingTree
import eu.antoniolopez.playground.threading.APPLICATION_BG
import eu.antoniolopez.playground.threading.APPLICATION_MAIN
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newFixedThreadPoolContext
import org.kodein.di.KodeinAware
import timber.log.Timber

abstract class BaseApplication : Application(), KodeinAware {

    override fun onCreate() {
        super.onCreate()
        setApplicationContext()
        setupStrictMode()
        setupThreadingContexts()
        setupTimber()
    }

    private fun setupStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectAll()
                    .penaltyLog()
                    .build()
            )
            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .penaltyLog()
                    .penaltyDeath()
                    .build()
            )
        }
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            // Set the debug logging.
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
    }

    private fun setupThreadingContexts() {
        APPLICATION_MAIN = Dispatchers.Main + CoroutineExceptionHandler { _, error ->
            throw error
        }
        APPLICATION_BG = newFixedThreadPoolContext(
            2 * Runtime.getRuntime().availableProcessors(),
            "bg"
        ) + CoroutineExceptionHandler { _, error -> throw error }
    }
}
