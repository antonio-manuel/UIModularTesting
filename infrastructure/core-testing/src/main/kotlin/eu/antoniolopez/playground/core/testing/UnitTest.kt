package eu.antoniolopez.playground.core.testing

import eu.antoniolopez.playground.threading.APPLICATION_BG
import eu.antoniolopez.playground.threading.APPLICATION_MAIN
import io.mockk.clearAllMocks
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance

@Suppress("IllegalIdentifier")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class UnitTest {
    @BeforeEach
    fun onBefore() {
        APPLICATION_MAIN = Dispatchers.Unconfined + CoroutineExceptionHandler { _, error -> throw error }
        APPLICATION_BG = Dispatchers.Unconfined + CoroutineExceptionHandler { _, error -> throw error }
        onPrepareBeforeEachTest()
    }

    @AfterEach
    fun onAfter() {
        clearAllMocks()
    }

    open fun onPrepareBeforeEachTest() {}
}
