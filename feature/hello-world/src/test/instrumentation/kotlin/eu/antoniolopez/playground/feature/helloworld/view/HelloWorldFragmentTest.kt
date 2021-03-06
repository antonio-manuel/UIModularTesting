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
import eu.antoniolopez.playground.navigation.Navigator
import io.mockk.mockk
import org.junit.Test
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

class HelloWorldFragmentTest : InstrumentationUnitTest() {

    private val mockPresenter: HelloWorldPresenter = mockk(relaxed = true)
    private val mockNavigator: Navigator = mockk(relaxed = true)

    override fun onRequestFragment(): HelloWorldFragment = HelloWorldFragment.newInstance()

    override fun onPrepareInjection() {
        featureComponent.addConfig {
            bind<HelloWorldPresenter>(overrides = true) with singleton { mockPresenter }
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
