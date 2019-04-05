package eu.antoniolopez.playground.feature.helloworld.presenter

import eu.antoniolopez.playground.core.view.lifecycle.LifecycleView
import eu.antoniolopez.playground.core.view.presentation.LifecyclePresenter
import eu.antoniolopez.playground.navigation.NavigationCommand
import eu.antoniolopez.playground.navigation.command.goodbyeworld.goodbyeWorldNavigationCommand

class HelloWorldPresenter : LifecyclePresenter<HelloWorldPresenter.View>() {

    fun onButton(text: String) {
        view?.renderText(text)
    }

    fun onNavigate() {
        view?.navigateTo(goodbyeWorldNavigationCommand())
    }

    interface View : LifecycleView {
        fun renderText(text: String)
        fun navigateTo(command: NavigationCommand)
    }
}
