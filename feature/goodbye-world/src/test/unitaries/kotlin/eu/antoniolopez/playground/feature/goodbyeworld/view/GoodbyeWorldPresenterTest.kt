package eu.antoniolopez.playground.feature.goodbyeworld.view

import eu.antoniolopez.playground.core.testing.UnitTest
import eu.antoniolopez.playground.core.view.lifecycle.LifecycleStream
import eu.antoniolopez.playground.feature.goodbyeworld.presenter.GoodbyeWorldPresenter
import eu.antoniolopez.playground.navigation.command.goodbyeworld.goodbyeWorldNavigationCommand
import eu.antoniolopez.playground.navigation.command.helloworld.helloWorldNavigationCommand
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

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
    fun `on navigate clicked then view navigates to hellocommand`() {
        sut.onViewReady(mockView)

        sut.onNavigate()

        verify { mockView.navigateTo(eq(helloWorldNavigationCommand())) }
    }

    @Test
    fun `on navigate clicked then view don't navigates to goodbyecommand`() {
        sut.onViewReady(mockView)

        sut.onNavigate()

        verify(inverse = true) { mockView.navigateTo(eq(goodbyeWorldNavigationCommand())) }
    }
}
