package eu.antoniolopez.playground.view
/*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import eu.antoniolopez.playground.R
import eu.antoniolopez.playground.core.view.testing.InstrumentationUnitTest
import eu.antoniolopez.playground.di.appComponent
import eu.antoniolopez.playground.presenter.MainPresenter
import io.mockk.mockk
import org.junit.Test
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

class AppFragmentTest : InstrumentationUnitTest() {

    private val mockPresenter: MainPresenter = mockk(relaxed = true)

    override fun onRequestFragment() = MainFragment.newInstance()

    override fun onPrepareBeforeEachTest() {
        appComponent.addConfig {
            bind<MainPresenter>(overrides = true) with provider { mockPresenter }
        }
        super.onPrepareBeforeEachTest()
    }

    //@Test
    fun scrollToItemBelowFold_checkItsText() {
        onView(ViewMatchers.withId(R.id.button_activity))
            .check(ViewAssertions.matches(ViewMatchers.withText("Navigate")))
    }
}
*/
