package eu.antoniolopez.playground.core.view.testing

import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import androidx.test.rule.ActivityTestRule
import com.nhaarman.mockitokotlin2.reset
import org.junit.After
import org.junit.Before
import org.junit.Rule

//https://medium.com/@aitorvs/isolate-your-fragments-just-for-testing-ea7d4fddcba2
abstract class InstrumentationUnitTest {
    val mocksCache = mutableListOf<Any>()

    private val fragment by lazy {
        onRequestFragment()
    }

    private val testActivityRule = ActivityTestRule(SingleFragmentActivity::class.java, true, true)

    abstract fun onRequestFragment(): Fragment

    @Rule
    fun rule() = testActivityRule

    @Before
    fun onBefore() {
        reset(*mocksCache.toTypedArray())
        onPrepareBeforeEachTest()
    }

    @After
    fun onAfterAll() {
        mocksCache.clear()
    }

    @SuppressLint("RestrictedApi")
    @Before
    open fun onPrepareBeforeEachTest() {
        rule().activity.setFragment(fragment)
    }

    inline fun <reified T : Any> mock(): T {
        val mockObject = com.nhaarman.mockitokotlin2.mock<T>()
        mocksCache.add(mockObject)
        return mockObject
    }
}
