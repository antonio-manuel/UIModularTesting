package eu.antoniolopez.playground.feature.goodbyeworld.view

import eu.antoniolopez.playground.core.testing.UnitTest
import eu.antoniolopez.playground.core.view.lifecycle.LifecycleStream
import eu.antoniolopez.playground.feature.goodbyeworld.presenter.GoodbyeNavigationPresenter
import eu.antoniolopez.playground.navigation.command.goodbyeworld.goodbyeWorldNavigationCommand
import eu.antoniolopez.playground.navigation.command.helloworld.helloWorldNavigationCommand
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class GoodbyeNavigationPresenterTest : UnitTest() {

    private lateinit var sut: GoodbyeNavigationPresenter
    private val mockView: GoodbyeNavigationPresenter.View = mockk(relaxUnitFun = true)

    override fun onPrepareBeforeEachTest() {
        every { mockView.lifecycle } returns LifecycleStream().subscribe()
        sut = GoodbyeNavigationPresenter()
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
