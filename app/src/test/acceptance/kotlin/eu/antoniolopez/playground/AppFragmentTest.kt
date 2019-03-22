package eu.antoniolopez.playground

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import eu.antoniolopez.playground.core.view.testing.InstrumentationUnitTest
import eu.antoniolopez.playground.feature.helloworld.di.featureComponent
import eu.antoniolopez.playground.presenter.MainPresenter
import eu.antoniolopez.playground.view.MainFragment
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

@RunWith(AndroidJUnit4::class)
@LargeTest
class AppFragmentTest : InstrumentationUnitTest() {

    private val mockPresenter: MainPresenter = MainPresenter()

    override fun onRequestFragment() = MainFragment.newInstance()

    override fun onPrepareBeforeEachTest() {
        featureComponent = Kodein.invoke(allowSilentOverride = true) {
            bind<MainPresenter>(overrides = true) with singleton { mockPresenter }
        }
        super.onPrepareBeforeEachTest()
    }

    @Test
    fun itemInMiddleOfList_hasSpecialText() {
        val appContext = ApplicationProvider.getApplicationContext<Application>()
        assertEquals("eu.antoniolopez.playgroundt", appContext.packageName)
    }
}
