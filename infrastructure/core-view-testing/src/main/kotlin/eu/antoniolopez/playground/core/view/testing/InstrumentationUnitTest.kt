package eu.antoniolopez.playground.core.view.testing

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.filters.MediumTest
import eu.antoniolopez.playground.core.view.testing.threading.CoroutineContextForTest
import eu.antoniolopez.playground.core.view.testing.view.SingleFragmentActivity
import eu.antoniolopez.playground.threading.APPLICATION_BG
import eu.antoniolopez.playground.threading.APPLICATION_MAIN
import io.mockk.clearAllMocks
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Rule
import kotlin.reflect.KClass

//https://medium.com/@aitorvs/isolate-your-fragments-just-for-testing-ea7d4fddcba2
@MediumTest
abstract class InstrumentationUnitTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<SingleFragmentActivity>()

    abstract fun onRequestFragment(): Fragment

    @Before
    fun onBefore() {
        APPLICATION_MAIN = Dispatchers.Main
        APPLICATION_BG = CoroutineContextForTest
        clearAllMocks()
        onPrepareInjection()
        onPrepareBeforeEachTest()
        setContentFragment()
    }

    private fun setContentFragment() {
        activityScenarioRule.scenario.onActivity { activity ->
            activity.supportFragmentManager.attach(android.R.id.content, onRequestFragment())
        }
    }

    open fun onPrepareInjection() {}

    open fun onPrepareBeforeEachTest() {}

    private fun FragmentManager.attach(@IdRes placeHolder: Int, fragment: Fragment) {
        val tag = getTag(fragment::class)
        beginTransaction()
            .replace(placeHolder, fragment, tag)
            .commitNow()
    }

    private fun getTag(type: KClass<*>) = type.java.name
}
