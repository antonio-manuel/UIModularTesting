package eu.antoniolopez.playground.core.view.testing

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.filters.MediumTest
import eu.antoniolopez.playground.core.view.testing.threading.CoroutineContextForTest
import eu.antoniolopez.playground.core.view.testing.view.SingleFragmentActivity
import eu.antoniolopez.playground.threading.APPLICATION_BG
import eu.antoniolopez.playground.threading.APPLICATION_MAIN
import io.mockk.clearAllMocks
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Rule
import kotlin.reflect.KClass

// https://medium.com/@aitorvs/isolate-your-fragments-just-for-testing-ea7d4fddcba2
@MediumTest
abstract class InstrumentationUnitTest {

    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<SingleFragmentActivity> = activityScenarioRule()

    abstract fun onRequestFragment(): Fragment

    @Before
    fun onBefore() {
        APPLICATION_MAIN = Dispatchers.Main + CoroutineExceptionHandler { _, error -> throw error }
        APPLICATION_BG = CoroutineContextForTest + CoroutineExceptionHandler { _, error -> throw error }
        clearAllMocks()
        onPrepareInjection()
        onPrepareBeforeEachTest()
        setContentFragment()
    }

    open fun onPrepareInjection() {}

    open fun onPrepareBeforeEachTest() {}

    private fun setContentFragment() {
        activityScenarioRule.scenario.onActivity { activity ->
            activity.supportFragmentManager.attach(android.R.id.content, onRequestFragment())
        }
    }

    private fun FragmentManager.attach(@IdRes placeHolder: Int, fragment: Fragment) {
        val tag = getTag(fragment::class)
        beginTransaction()
            .replace(placeHolder, fragment, tag)
            .commitAllowingStateLoss()
    }

    private fun getTag(type: KClass<*>): String = type.java.name
}
