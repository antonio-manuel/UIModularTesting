package eu.antoniolopez.playground.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import eu.antoniolopez.playground.R
import eu.antoniolopez.playground.core.view.testing.InstrumentationUnitTest
import eu.antoniolopez.playground.di.appComponent
import eu.antoniolopez.playground.navigation.Navigator
import eu.antoniolopez.playground.presenter.MainPresenter
import io.mockk.mockk
import org.junit.Test
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

class AppFragmentTest : InstrumentationUnitTest() {

    private val mockPresenter: MainPresenter = mockk(relaxed = true)
    private val mockNavigator: Navigator = mockk(relaxed = true)

    override fun onRequestFragment() = MainFragment.newInstance()

    override fun onPrepareInjection() {
        appComponent.addConfig {
            bind<MainPresenter>(overrides = true) with singleton { mockPresenter }
            bind<Navigator>(overrides = true) with singleton { mockNavigator }
        }
    }

    @Test
    fun checkButtonText() {
        onView(ViewMatchers.withId(R.id.button_activity))
            .perform(ViewActions.click())
            .check(ViewAssertions.matches(ViewMatchers.withText("Navigate")))
    }
}
