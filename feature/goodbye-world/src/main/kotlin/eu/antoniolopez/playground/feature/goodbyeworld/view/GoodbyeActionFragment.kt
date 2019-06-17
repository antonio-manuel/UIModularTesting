package eu.antoniolopez.playground.feature.goodbyeworld.view

import android.os.Bundle
import eu.antoniolopez.playground.core.view.BaseFragment
import eu.antoniolopez.playground.core.view.extension.hideKeyboardFrom
import eu.antoniolopez.playground.core.view.extension.onClickDebounced
import eu.antoniolopez.playground.feature.goodbyeworld.R
import eu.antoniolopez.playground.feature.goodbyeworld.di.featureComponent
import eu.antoniolopez.playground.feature.goodbyeworld.presenter.GoodbyeActionPresenter
import kotlinx.android.synthetic.main.goodbye_action_fragment.*
import org.kodein.di.generic.instance

class GoodbyeActionFragment
    : BaseFragment(), GoodbyeActionPresenter.View {

    companion object {
        fun newInstance() = GoodbyeActionFragment()
    }

    private val presenter: GoodbyeActionPresenter by featureComponent.instance()

    override fun onRequestLayoutResourceId(): Int = R.layout.goodbye_action_fragment

    override fun onViewReady(savedInstanceState: Bundle?) {
        presenter.onViewReady(this)
    }

    override fun onResume() {
        super.onResume()
        changeTextBt.onClickDebounced {
            presenter.onButton(editTextUserInput.text.toString())
            requireContext().hideKeyboardFrom(changeTextBt)
        }
    }

    override fun renderText(text: String) {
        textToBeChanged.text = text
    }
}
