package eu.antoniolopez.playground.core.testing

import androidx.test.filters.SmallTest
import com.nhaarman.mockitokotlin2.reset
import eu.antoniolopez.playground.core.testing.threading.CoroutineContextForTest
import eu.antoniolopez.playground.threading.APPLICATION_BG
import eu.antoniolopez.playground.threading.APPLICATION_MAIN
import org.junit.After
import org.junit.Before

@Suppress("IllegalIdentifier")
@SmallTest
abstract class UnitTest {
    val mocksCache = mutableListOf<Any>()

    @Before
    fun onBefore() {
        APPLICATION_MAIN = CoroutineContextForTest
        APPLICATION_BG = CoroutineContextForTest
        reset(*mocksCache.toTypedArray())
        onPrepareBeforeEachTest()
    }

    @After
    fun onAfter() {
        mocksCache.clear()
    }

    inline fun <reified T : Any> mock(): T {
        val mockObject = com.nhaarman.mockitokotlin2.mock<T>()
        mocksCache.add(mockObject)
        return mockObject
    }

    open fun onPrepareBeforeEachTest() {}
}
