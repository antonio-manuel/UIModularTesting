package eu.antoniolopez.playground.presenter

import eu.antoniolopez.playground.core.view.lifecycle.LifecycleView
import eu.antoniolopez.playground.core.view.presentation.LifecyclePresenter
import eu.antoniolopez.playground.navigation.NavigationCommand
import eu.antoniolopez.playground.navigation.command.helloworld.helloWorldNavigationCommand

class MainPresenter : LifecyclePresenter<MainPresenter.View>() {

    override fun onViewAttached(firstTime: Boolean) {
        super.onViewAttached(firstTime)
        view?.navigateTo(helloWorldNavigationCommand())
    }

    interface View : LifecycleView {
        fun navigateTo(command: NavigationCommand)
    }
}
