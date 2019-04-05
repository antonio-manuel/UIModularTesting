package eu.antoniolopez.playground.feature.helloworld.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import eu.antoniolopez.playground.core.view.testing.InstrumentationUnitTest
import eu.antoniolopez.playground.feature.helloworld.R
import eu.antoniolopez.playground.feature.helloworld.di.featureComponent
import eu.antoniolopez.playground.feature.helloworld.presenter.HelloWorldPresenter
import io.mockk.mockk
import org.junit.Test
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

class HelloWorldFragmentTest : InstrumentationUnitTest() {

    private val mockPresenter : HelloWorldPresenter = mockk(relaxed = true)

    override fun onRequestFragment() = HelloWorldFragment.newInstance()

    override fun onPrepareBeforeEachTest() {
        featureComponent.addConfig {
            bind<HelloWorldPresenter>(overrides = true) with singleton { mockPresenter }
        }
    }

    @Test
    fun checkButtonText() {
        onView(withId(R.id.changeTextBt))
            .perform(click())
            .check(matches(withText("Change text")))
    }
}
