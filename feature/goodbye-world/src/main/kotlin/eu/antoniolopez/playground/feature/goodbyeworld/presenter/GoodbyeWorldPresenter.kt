package eu.antoniolopez.playground.feature.goodbyeworld.presenter

import eu.antoniolopez.playground.core.view.lifecycle.LifecycleView
import eu.antoniolopez.playground.core.view.presentation.LifecyclePresenter
import eu.antoniolopez.playground.navigation.NavigationCommand
import eu.antoniolopez.playground.navigation.command.helloworld.helloWorldNavigationCommand

class GoodbyeWorldPresenter : LifecyclePresenter<GoodbyeWorldPresenter.View>() {

    fun onButton(text: String) {
        view?.renderText(text)
    }

    fun onNavigate() {
        view?.navigateTo(helloWorldNavigationCommand())
    }

    interface View : LifecycleView {
        fun renderText(text: String)
        fun navigateTo(command: NavigationCommand)
    }
}
