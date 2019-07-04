package eu.antoniolopez.playground.feature.goodbyeworld.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import eu.antoniolopez.playground.core.view.testing.InstrumentationUnitTest
import eu.antoniolopez.playground.feature.goodbyeworld.R
import eu.antoniolopez.playground.feature.goodbyeworld.di.featureComponent
import eu.antoniolopez.playground.feature.goodbyeworld.presenter.GoodbyeActionPresenter
import io.mockk.mockk
import org.junit.Test
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

class GoodbyeActionFragmentTest : InstrumentationUnitTest() {

    private val mockPresenter: GoodbyeActionPresenter = mockk(relaxed = true)

    override fun onRequestFragment(): GoodbyeActionFragment = GoodbyeActionFragment.newInstance()

    override fun onPrepareInjection() {
        featureComponent.addConfig {
            bind<GoodbyeActionPresenter>(overrides = true) with singleton { mockPresenter }
        }
    }

    @Test
    fun checkButtonText() {
        onView(withId(R.id.changeTextBt))
            .perform(click())
            .check(matches(withText("Change text")))
    }
}
