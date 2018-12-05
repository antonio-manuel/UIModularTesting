package eu.antoniolopez.base

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import eu.antoniolopez.base.di.featureComponent
import eu.antoniolopez.base.presenter.ButtonPresenter
import eu.antoniolopez.base.view.ButtonFragment
import eu.antoniolopez.playground.core.view.testing.InstrumentationUnitTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ButtonFragmentTest : InstrumentationUnitTest() {

    private val mockPresenter : ButtonPresenter = ButtonPresenter()

    override fun onRequestFragment() = ButtonFragment.newInstance()

    override fun onPrepareBeforeEachTest() {
        featureComponent = Kodein.invoke(allowSilentOverride = true) {
            bind<ButtonPresenter>(overrides = true) with singleton { mockPresenter }
        }
        super.onPrepareBeforeEachTest()
    }

    @Test
    fun scrollToItemBelowFold_checkItsText() {
        onView(withId(R.id.button))
            .perform(click())
            .check(matches(withText("Clicked")))
    }

    @Test
    fun itemInMiddleOfList_hasSpecialText() {
        val appContext = ApplicationProvider.getApplicationContext<Application>()
        assertEquals("eu.antoniolopez.base.test", appContext.packageName)
    }
}
