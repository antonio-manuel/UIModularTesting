package eu.antoniolopez.playground.threading.stream

import kotlinx.coroutines.channels.ConflatedBroadcastChannel

abstract class BehaviourStream<T> : Stream<T> {
    private val stream = ConflatedBroadcastChannel<T>()

    override fun subscribe() = stream.openSubscription()

    fun next(element: T) {
        stream.valueOrNull?.let {
            stream.offer(element)
        } ?: stream.offer(element)
    }

    fun valueOrNull() = stream.valueOrNull
}
