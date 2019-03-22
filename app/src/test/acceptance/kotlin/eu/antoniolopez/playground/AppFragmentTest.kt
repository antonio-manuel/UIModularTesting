package eu.antoniolopez.playground

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import eu.antoniolopez.playground.view.MainActivity
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class AppFragmentTest {

    private val STRING_TO_BE_TYPED = "Espresso"

    @get:Rule
    var testActivityRule = activityScenarioRule<MainActivity>()

    @Test
    fun replaceText() {
        onView(ViewMatchers.withId(eu.antoniolopez.playground.R.id.editTextUserInput))
            .perform(ViewActions.typeText(STRING_TO_BE_TYPED), ViewActions.closeSoftKeyboard())
            .check(ViewAssertions.matches(ViewMatchers.withText(STRING_TO_BE_TYPED)))
    }

    @Test
    fun packageFromRuleIsTheSameAsSupposed() {
        val appContext = ApplicationProvider.getApplicationContext<Application>()
        assertEquals("eu.antoniolopez.playground", appContext.packageName)
    }
}
