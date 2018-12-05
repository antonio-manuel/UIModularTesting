package eu.antoniolopez.base.presenter

import eu.antoniolopez.playground.core.annotation.Mockable

@Mockable
class ButtonPresenter {
    private lateinit var view: ButtonPresenter.View

    fun onViewAttached(view: ButtonPresenter.View) {
        this.view = view
    }

    fun onButton() {
        view.setText("Clicked")
    }

    interface View {
        fun setText(text: String)
    }
}
