package eu.antoniolopez.playground.core.view.lifecycle

import eu.antoniolopez.playground.threading.stream.BehaviourStream
import kotlinx.coroutines.channels.ReceiveChannel

interface LifecycleView {
    val lifecycle: ReceiveChannel<Lifecycle>

    enum class Lifecycle {
        CREATED, INITIALIZED, RESUME, PAUSE, STOP, START, DESTROYED
    }
}

class LifecycleStream : BehaviourStream<LifecycleView.Lifecycle>()
