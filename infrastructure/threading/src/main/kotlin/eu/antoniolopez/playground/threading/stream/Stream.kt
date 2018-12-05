package eu.antoniolopez.playground.threading.stream

import kotlinx.coroutines.channels.ReceiveChannel

interface Stream<T> {
    fun subscribe(): ReceiveChannel<T>
}
