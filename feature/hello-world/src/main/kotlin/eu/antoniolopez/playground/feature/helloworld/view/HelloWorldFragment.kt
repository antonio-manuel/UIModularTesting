package eu.antoniolopez.playground.feature.helloworld.view

import android.os.Bundle
import eu.antoniolopez.playground.core.view.BaseFragment
import eu.antoniolopez.playground.core.view.extension.hideKeyboardFrom
import eu.antoniolopez.playground.core.view.extension.onClickDebounced
import eu.antoniolopez.playground.feature.helloworld.R
import eu.antoniolopez.playground.feature.helloworld.di.featureComponent
import eu.antoniolopez.playground.feature.helloworld.presenter.HelloWorldPresenter
import eu.antoniolopez.playground.navigation.NavigationCommand
import eu.antoniolopez.playground.navigation.Navigator
import kotlinx.android.synthetic.main.hello_fragment.*
import org.kodein.di.generic.instance

class HelloWorldFragment
    : BaseFragment(), HelloWorldPresenter.View {

    companion object {
        fun newInstance() = HelloWorldFragment()
    }

    private val presenter: HelloWorldPresenter by featureComponent.instance()
    private val navigator: Navigator by featureComponent.instance()

    override fun onRequestLayoutResourceId(): Int = R.layout.hello_fragment

    override fun onViewReady(savedInstanceState: Bundle?) {
        presenter.onViewReady(this)
    }

    override fun onResume() {
        super.onResume()
        changeTextBt.onClickDebounced {
            presenter.onButton(editTextUserInput.text.toString())
            requireContext().hideKeyboardFrom(changeTextBt)
        }
        navigateBt.onClickDebounced { presenter.onNavigate() }
    }

    override fun renderText(text: String) {
        textToBeChanged.text = text
    }

    override fun navigateTo(command: NavigationCommand) {
        navigator.navigate(requireContext(), command)
        requireActivity().finish()
    }
}
