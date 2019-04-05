package eu.antoniolopez.playground.feature.goodbyeworld.view

import android.os.Bundle
import eu.antoniolopez.playground.core.view.BaseFragment
import eu.antoniolopez.playground.core.view.extension.hideKeyboardFrom
import eu.antoniolopez.playground.core.view.extension.onClickDebounced
import eu.antoniolopez.playground.feature.goodbyeworld.R
import eu.antoniolopez.playground.feature.goodbyeworld.di.featureComponent
import eu.antoniolopez.playground.feature.goodbyeworld.presenter.GoodbyeWorldPresenter
import eu.antoniolopez.playground.navigation.NavigationCommand
import eu.antoniolopez.playground.navigation.Navigator
import kotlinx.android.synthetic.main.goodbye_fragment.*
import org.kodein.di.generic.instance

class GoodbyeWorldFragment
    : BaseFragment(), GoodbyeWorldPresenter.View {

    companion object {
        fun newInstance() = GoodbyeWorldFragment()
    }

    private val presenter: GoodbyeWorldPresenter by featureComponent.instance()
    private val navigator: Navigator by featureComponent.instance()

    override fun onRequestLayoutResourceId(): Int = R.layout.goodbye_fragment

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
