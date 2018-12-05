package eu.antoniolopez.playground

import android.app.Application
import androidx.test.core.app.ApplicationProvider
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

    @get:Rule
    var testActivityRule = activityScenarioRule<MainActivity>()

    @Test
    fun packageFromRuleIsTheSameAsSupposed() {
        val appContext = ApplicationProvider.getApplicationContext<Application>()
        assertEquals("eu.antoniolopez.playground", appContext.packageName)
    }
}
