package eu.antoniolopez.playground.core.view.lifecycle

import android.os.Bundle
import android.support.v4.app.Fragment
import eu.antoniolopez.playground.core.view.lifecycle.LifecycleView.Lifecycle
import kotlinx.coroutines.channels.ReceiveChannel

abstract class LifecycleFragment : Fragment(), LifecycleView {
    private val lifecycleStream = LifecycleStream()
    private var lifecycleStatus = Lifecycle.CREATED
        set(value) {
            lifecycleStream.next(value)
        }
    override val lifecycle: ReceiveChannel<Lifecycle>
        get() = lifecycleStream.subscribe()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            lifecycleStatus = Lifecycle.CREATED
        }
        lifecycleStatus = Lifecycle.INITIALIZED
    }

    override fun onResume() {
        super.onResume()
        lifecycleStatus = Lifecycle.RESUME
    }

    override fun onStart() {
        super.onStart()
        lifecycleStatus = Lifecycle.START
    }

    override fun onPause() {
        super.onPause()
        lifecycleStatus = Lifecycle.PAUSE
    }

    override fun onStop() {
        super.onStop()
        lifecycleStatus = Lifecycle.STOP
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleStatus = Lifecycle.DESTROYED
    }
}
