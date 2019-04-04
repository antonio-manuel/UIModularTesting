package eu.antoniolopez.playground.feature.helloworld.presenter

import eu.antoniolopez.playground.core.view.lifecycle.LifecycleView
import eu.antoniolopez.playground.core.view.presentation.LifecyclePresenter

class HelloWorldPresenter : LifecyclePresenter<HelloWorldPresenter.View>() {

    fun onButton(text: String) {
        view?.renderText(text)
    }

    interface View : LifecycleView {
        fun renderText(text: String)
    }
}
