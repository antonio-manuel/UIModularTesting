package eu.antoniolopez.playground.feature.helloworld.view

import eu.antoniolopez.playground.core.testing.UnitTest
import eu.antoniolopez.playground.core.view.lifecycle.LifecycleStream
import eu.antoniolopez.playground.feature.helloworld.presenter.HelloWorldPresenter
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class HelloWorldPresenterTest : UnitTest() {

    private lateinit var sut: HelloWorldPresenter
    private val mockView: HelloWorldPresenter.View = mockk(relaxUnitFun = true)

    override fun onPrepareBeforeEachTest() {
        every { mockView.lifecycle } returns LifecycleStream().subscribe()
        sut = HelloWorldPresenter()
    }

    @Test
    fun `on button clicked then view renders text`() {
        val text = "HELLO"
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
