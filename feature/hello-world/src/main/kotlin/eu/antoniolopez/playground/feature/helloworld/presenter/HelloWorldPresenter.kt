package eu.antoniolopez.playground.feature.helloworld.presenter

import eu.antoniolopez.playground.core.view.lifecycle.LifecycleView
import eu.antoniolopez.playground.core.view.presentation.LifecyclePresenter

class HelloWorldPresenter : LifecyclePresenter<HelloWorldPresenter.View>() {

    fun onButton() {
        view?.setText("Clicked")
    }

    interface View : LifecycleView {
        fun setText(text: String)
    }
}
