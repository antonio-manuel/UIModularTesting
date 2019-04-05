package eu.antoniolopez.playground.feature.goodbyeworld.view

import eu.antoniolopez.playground.core.testing.UnitTest
import eu.antoniolopez.playground.core.view.lifecycle.LifecycleStream
import eu.antoniolopez.playground.feature.goodbyeworld.presenter.GoodbyeWorldPresenter
import io.mockk.*
import org.junit.Test

class GoodbyeWorldPresenterTest : UnitTest() {

    private lateinit var sut: GoodbyeWorldPresenter
    private val mockView: GoodbyeWorldPresenter.View = mockk(relaxUnitFun = true)

    override fun onPrepareBeforeEachTest() {
        every { mockView.lifecycle } returns LifecycleStream().subscribe()
        sut = GoodbyeWorldPresenter()
    }

    @Test
    fun `on button clicked then view renders text`() {
        val text = "GOODBYE"
        sut.onViewReady(mockView)

        sut.onButton(text)

        verify { mockView.renderText(text) }
    }

    @Test
    fun `on navigate clicked then view navigates`() {
        sut.onViewReady(mockView)

        sut.onNavigate()

        verify { mockView.navigateTo(any()) }
    }
}
