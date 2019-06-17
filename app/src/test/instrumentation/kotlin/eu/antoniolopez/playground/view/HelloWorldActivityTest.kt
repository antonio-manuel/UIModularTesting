package eu.antoniolopez.playground.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import eu.antoniolopez.playground.R
import eu.antoniolopez.playground.feature.helloworld.di.featureComponent
import eu.antoniolopez.playground.feature.helloworld.presenter.HelloWorldPresenter
import eu.antoniolopez.playground.feature.helloworld.view.HelloWorldActivity
import io.mockk.clearAllMocks
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

@RunWith(AndroidJUnit4::class)
@LargeTest
class HelloWorldActivityTest {

    private val mockPresenter: HelloWorldPresenter = mockk(relaxed = true)

    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<HelloWorldActivity> = activityScenarioRule()

    @Before
    fun prepareKodein() {
        clearAllMocks()
        featureComponent.addConfig {
            bind<HelloWorldPresenter>(overrides = true) with provider { mockPresenter }
        }
    }

    @Test
    fun checkOpens() {
        onView(withId(R.id.content))
    }

    @Test
    fun checkButtonText() {
        onView(withId(R.id.textToBeChanged))
            .perform(click())
            .check(matches(withText("Hello world")))
    }
}
