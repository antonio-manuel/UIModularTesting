package eu.antoniolopez.playground.core.testing.threading

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import kotlin.coroutines.CoroutineContext

object CoroutineContextForTest : CoroutineDispatcher() {
    override fun isDispatchNeeded(context: CoroutineContext): Boolean = false
    override fun dispatch(context: CoroutineContext, block: Runnable): Unit = block.run()
    override fun toString(): String = "Current"
}
