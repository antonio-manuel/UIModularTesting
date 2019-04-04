package eu.antoniolopez.playground.core.testing

import androidx.test.filters.SmallTest
import eu.antoniolopez.playground.core.testing.threading.CoroutineContextForTest
import eu.antoniolopez.playground.threading.APPLICATION_BG
import eu.antoniolopez.playground.threading.APPLICATION_MAIN
import io.mockk.clearAllMocks
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
        onPrepareBeforeEachTest()
    }

    @After
    fun onAfter() {
        clearAllMocks()
    }

    open fun onPrepareBeforeEachTest() {}
}
