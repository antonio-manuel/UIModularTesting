package eu.antoniolopez.playground.feature.goodbyeworld.presenter

import eu.antoniolopez.playground.core.view.lifecycle.LifecycleView
import eu.antoniolopez.playground.core.view.presentation.LifecyclePresenter

class GoodbyeActionPresenter : LifecyclePresenter<GoodbyeActionPresenter.View>() {

    fun onButton(text: String) {
        view?.renderText(text)
    }

    interface View : LifecycleView {
        fun renderText(text: String)
    }
}
