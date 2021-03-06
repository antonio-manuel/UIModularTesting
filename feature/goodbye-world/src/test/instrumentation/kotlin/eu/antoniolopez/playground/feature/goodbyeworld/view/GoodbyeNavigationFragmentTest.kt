package eu.antoniolopez.playground.feature.goodbyeworld.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import eu.antoniolopez.playground.core.view.testing.InstrumentationUnitTest
import eu.antoniolopez.playground.feature.goodbyeworld.R
import eu.antoniolopez.playground.feature.goodbyeworld.di.featureComponent
import eu.antoniolopez.playground.feature.goodbyeworld.presenter.GoodbyeNavigationPresenter
import eu.antoniolopez.playground.navigation.Navigator
import io.mockk.mockk
import org.junit.Test
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

class GoodbyeNavigationFragmentTest : InstrumentationUnitTest() {

    private val mockPresenter: GoodbyeNavigationPresenter = mockk(relaxed = true)
    private val mockNavigator: Navigator = mockk(relaxed = true)

    override fun onRequestFragment(): GoodbyeActionFragment = GoodbyeActionFragment.newInstance()

    override fun onPrepareInjection() {
        featureComponent.addConfig {
            bind<GoodbyeNavigationPresenter>(overrides = true) with singleton { mockPresenter }
            bind<Navigator>(overrides = true) with singleton { mockNavigator }
        }
    }

    @Test
    fun checkButtonText() {
        onView(withId(R.id.changeTextBt))
            .perform(click())
            .check(matches(withText("Change text")))
    }
}
