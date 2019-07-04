package eu.antoniolopez.playground.threading

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.suspendCoroutine

lateinit var APPLICATION_MAIN: CoroutineContext
lateinit var APPLICATION_BG: CoroutineContext

fun perform(block: suspend CoroutineScope.() -> Unit) {
    CoroutineScope(APPLICATION_MAIN).launch { block() }
}

fun <T> bg(block: suspend CoroutineScope.() -> T): Deferred<T> {
    val process = CoroutineScope(APPLICATION_BG).async { block() }
    process.invokeOnCompletion { error -> error?.let { perform { throw it } } }
    return process
}

suspend fun <T> waitUntil(block: (Continuation<T>) -> Unit) = suspendCoroutine<T> { block(it) }

fun <T> Deferred<T>.onCompletion(block: () -> Unit): Deferred<T> {
    this.invokeOnCompletion { block() }
    return this
}

fun <T> Deferred<T>.finish() {
    this.cancel()
}

fun <T> ReceiveChannel<T>.finish() {
    this.cancel()
}

fun Job.finish() {
    this.cancel()
}
