package eu.antoniolopez.playground.core.view.testing

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import eu.antoniolopez.playground.core.view.testing.view.SingleFragmentActivity
import io.mockk.clearAllMocks
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import kotlin.reflect.KClass

//https://medium.com/@aitorvs/isolate-your-fragments-just-for-testing-ea7d4fddcba2
@RunWith(AndroidJUnit4::class)
@LargeTest
abstract class InstrumentationUnitTest {

    private val fragment by lazy {
        onRequestFragment()
    }

    @get:Rule
    var activityScenarioRule = activityScenarioRule<SingleFragmentActivity>()

    abstract fun onRequestFragment(): Fragment

    @Before
    fun onBefore() {
        clearAllMocks()
        setContentFragment()
        onPrepareBeforeEachTest()
    }

    private fun setContentFragment() {
        activityScenarioRule.scenario.onActivity { activity ->
            activity.supportFragmentManager.attach(android.R.id.content, onRequestFragment())
        }
    }

    open fun onPrepareBeforeEachTest() {}


    private fun FragmentManager.attach(@IdRes placeHolder: Int, fragment: Fragment) {
        val tag = getTag(fragment::class)
        beginTransaction()
            .replace(placeHolder, fragment, tag)
            .commitNow()
    }

    private fun getTag(type: KClass<*>) = type.java.name
}
