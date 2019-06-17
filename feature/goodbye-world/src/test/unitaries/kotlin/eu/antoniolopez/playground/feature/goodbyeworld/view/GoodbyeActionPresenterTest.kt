package eu.antoniolopez.playground.feature.goodbyeworld.view

import eu.antoniolopez.playground.core.testing.UnitTest
import eu.antoniolopez.playground.core.view.lifecycle.LifecycleStream
import eu.antoniolopez.playground.feature.goodbyeworld.presenter.GoodbyeActionPresenter
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class GoodbyeActionPresenterTest : UnitTest() {

    private lateinit var sut: GoodbyeActionPresenter
    private val mockView: GoodbyeActionPresenter.View = mockk(relaxUnitFun = true)

    override fun onPrepareBeforeEachTest() {
        every { mockView.lifecycle } returns LifecycleStream().subscribe()
        sut = GoodbyeActionPresenter()
    }

    @Test
    fun `on button clicked then view renders text`() {
        val text = "GOODBYE"
        sut.onViewReady(mockView)

        sut.onButton(text)

        verify { mockView.renderText(text) }
    }
}
