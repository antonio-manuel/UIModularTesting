package eu.antoniolopez.playground.threading.stream

import kotlinx.coroutines.channels.BroadcastChannel

abstract class PublishStream<T> : Stream<T> {
    private val stream = BroadcastChannel<T>(1)

    override fun subscribe() = stream.openSubscription()

    fun next(element: T) {
        stream.offer(element)
    }
}
