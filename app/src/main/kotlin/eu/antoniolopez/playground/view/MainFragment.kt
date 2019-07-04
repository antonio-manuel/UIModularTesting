package eu.antoniolopez.playground.view

import android.os.Bundle
import eu.antoniolopez.playground.R
import eu.antoniolopez.playground.core.view.BaseFragment
import eu.antoniolopez.playground.core.view.extension.onClickDebounced
import eu.antoniolopez.playground.di.appComponent
import eu.antoniolopez.playground.navigation.NavigationCommand
import eu.antoniolopez.playground.navigation.Navigator
import eu.antoniolopez.playground.presenter.MainPresenter
import kotlinx.android.synthetic.main.main_fragment.*
import org.kodein.di.generic.instance

class MainFragment : BaseFragment(), MainPresenter.View {

    companion object {
        fun newInstance(): MainFragment = MainFragment()
    }

    private val presenter: MainPresenter by appComponent.instance()
    private val navigator: Navigator by appComponent.instance()

    override fun onRequestLayoutResourceId(): Int = R.layout.main_fragment

    override fun onViewReady(savedInstanceState: Bundle?) {
        presenter.onViewReady(this)
        button_activity.onClickDebounced { presenter.onButton() }
    }

    override fun navigateTo(command: NavigationCommand) {
        navigator.navigate(requireContext(), command)
        requireActivity().finish()
    }
}
