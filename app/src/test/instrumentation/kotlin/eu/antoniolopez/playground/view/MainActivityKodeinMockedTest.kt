package eu.antoniolopez.playground.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import eu.antoniolopez.playground.R
import eu.antoniolopez.playground.di.appComponent
import eu.antoniolopez.playground.presenter.MainPresenter
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
class MainActivityKodeinMockedTest {

    private val mockPresenter: MainPresenter = mockk(relaxed = true)

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Before
    fun prepareKodein() {
        clearAllMocks()
        appComponent.addConfig {
            bind<MainPresenter>(overrides = true) with provider { mockPresenter }
        }
    }

    @Test
    fun checkOpens() {
        onView(withId(R.id.content))
    }

    @Test
    fun checkButtonText() {
        onView(withId(R.id.button_activity))
            .perform(click())
            .check(matches(withText("Navigate")))
    }
}
