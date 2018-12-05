package eu.antoniolopez.playground.application

import android.app.Application
import android.os.StrictMode
import eu.antoniolopez.playground.BuildConfig
import eu.antoniolopez.playground.threading.APPLICATION_BG
import eu.antoniolopez.playground.threading.APPLICATION_MAIN
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newFixedThreadPoolContext

class PlayGroundApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupStrictMode()
        setupThreadingContexts()
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

    private fun setupThreadingContexts() {
        APPLICATION_MAIN = Dispatchers.Main
        APPLICATION_BG = newFixedThreadPoolContext(
            2 * Runtime.getRuntime().availableProcessors(),
            "bg"
        ) + CoroutineExceptionHandler { _, error -> throw error }

    }
}
