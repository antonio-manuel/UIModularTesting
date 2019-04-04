package eu.antoniolopez.playground.core.view.testing

import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import io.mockk.clearAllMocks
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

//https://medium.com/@aitorvs/isolate-your-fragments-just-for-testing-ea7d4fddcba2
@RunWith(AndroidJUnit4::class)
@LargeTest
abstract class InstrumentationUnitTest {

    private val fragment by lazy {
        onRequestFragment()
    }

    private val testActivityRule = ActivityTestRule(SingleFragmentActivity::class.java, true, true)

    abstract fun onRequestFragment(): Fragment

    @Rule
    fun rule() = testActivityRule

    @Before
    fun onBefore() {
        clearAllMocks()
        onPrepareBeforeEachTest()
    }

    @After
    fun onAfterAll() {
        testActivityRule.finishActivity()
    }

    @SuppressLint("RestrictedApi")
    @Before
    open fun onPrepareBeforeEachTest() {
        rule().activity.setFragment(fragment)
    }
}
