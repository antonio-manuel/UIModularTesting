package eu.antoniolopez.playground.feature.helloworld.view

import android.os.Bundle
import eu.antoniolopez.playground.core.view.BaseFragment
import eu.antoniolopez.playground.core.view.extension.hideKeyboardFrom
import eu.antoniolopez.playground.core.view.extension.onClickDebounced
import eu.antoniolopez.playground.feature.helloworld.R
import eu.antoniolopez.playground.feature.helloworld.di.featureComponent
import eu.antoniolopez.playground.feature.helloworld.presenter.HelloWorldPresenter
import kotlinx.android.synthetic.main.fragment_button.*
import org.kodein.di.generic.instance

class HelloWorldFragment
    : BaseFragment(), HelloWorldPresenter.View {

    companion object {
        fun newInstance() = HelloWorldFragment()
    }

    private val presenter: HelloWorldPresenter by featureComponent.instance()

    override fun onRequestLayoutResourceId(): Int = R.layout.fragment_button

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
