package eu.antoniolopez.playground.core.testing

import eu.antoniolopez.playground.core.testing.threading.CoroutineContextForTest
import eu.antoniolopez.playground.threading.APPLICATION_BG
import eu.antoniolopez.playground.threading.APPLICATION_MAIN
import io.mockk.clearAllMocks
import kotlinx.coroutines.CoroutineExceptionHandler
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance

@Suppress("IllegalIdentifier")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class UnitTest {
    @BeforeEach
    fun onBefore() {
        APPLICATION_MAIN = CoroutineContextForTest + CoroutineExceptionHandler { _, error -> throw error }
        APPLICATION_BG = CoroutineContextForTest + CoroutineExceptionHandler { _, error -> throw error }
        onPrepareBeforeEachTest()
    }

    @AfterEach
    fun onAfter() {
        clearAllMocks()
    }

    open fun onPrepareBeforeEachTest() {}
}
