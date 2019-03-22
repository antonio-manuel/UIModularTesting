package eu.antoniolopez.playground.core.view.presentation

import eu.antoniolopez.playground.core.view.lifecycle.LifecycleView
import eu.antoniolopez.playground.threading.APPLICATION_BG
import eu.antoniolopez.playground.threading.perform
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach

abstract class LifecyclePresenter<T : LifecycleView> : CoroutineScope {
    protected var view: T? = null
    override val coroutineContext = APPLICATION_BG + SupervisorJob()
    private val lifecycle: ReceiveChannel<LifecycleView.Lifecycle>?
        get() = view?.lifecycle

    fun onViewReady(view: T, firstTime: Boolean = false) {
        this.view = view
        subscribeToViewLifecycle()
        onViewAttached(firstTime)
    }

    protected fun onViewDestroyed() {}

    private fun subscribeToViewLifecycle() {
        async {
            lifecycle?.consumeEach {
                when (it) {
                    LifecycleView.Lifecycle.RESUME -> onResume()
                    LifecycleView.Lifecycle.PAUSE -> onPause()
                    LifecycleView.Lifecycle.DESTROYED -> onDestroyed()
                    LifecycleView.Lifecycle.CREATED -> onCreated()
                    LifecycleView.Lifecycle.INITIALIZED -> onInitialized()
                    LifecycleView.Lifecycle.STOP -> onStop()
                    LifecycleView.Lifecycle.START -> onStart()
                }
            }
        }.invokeOnCompletion {
            lifecycle?.cancel()
        }
    }

    protected fun <T> Deferred<T>.takeUntil(match: (LifecycleView.Lifecycle) -> Boolean): Deferred<T> {
        async {
            lifecycle?.consumeEach {
                if (match(it)) {
                    this@takeUntil.cancel()
                }
            }
        }
        return this
    }

    protected open fun onViewAttached(firstTime: Boolean = false) {}

    protected open fun onResume() {}

    protected open fun onPause() {}

    private fun onDestroyed() {
        perform { onViewDestroyed() }
        view = null
        coroutineContext.cancelChildren()
    }

    protected open fun onCreated() {}

    protected open fun onInitialized() {}

    protected open fun onStop() {}

    protected open fun onStart() {}

    protected fun <T> bg(execution: suspend CoroutineScope.() -> T) =
        async { execution() }

    protected fun <T> bg(
        onRun: suspend CoroutineScope.() -> T,
        onCancel: () -> Unit = { }
    )
            : Deferred<T> {
        return async {
            onRun()
        }.takeUntil({ it == LifecycleView.Lifecycle.DESTROYED }, onCancel)
    }

    @Suppress("UNNECESSARY_SAFE_CALL", "MemberVisibilityCanPrivate")
    private fun <T> Deferred<T>.takeUntil(
        condition: (LifecycleView.Lifecycle) -> Boolean,
        onCancel: () -> Unit = { }
    ): Deferred<T> {
        perform {
            view?.lifecycle?.consumeEach {
                if (condition(it)) {
                    this@takeUntil.cancel()
                    view?.lifecycle?.cancel()
                    onCancel()
                }
            }
        }
        return this
    }

    protected suspend fun <T> await(block: suspend () -> T): T = withContext(coroutineContext) { block() }
}
