package eu.antoniolopez.playground.feature.goodbyeworld.view

import android.os.Bundle
import eu.antoniolopez.playground.core.view.BaseFragment
import eu.antoniolopez.playground.core.view.extension.onClickDebounced
import eu.antoniolopez.playground.feature.goodbyeworld.R
import eu.antoniolopez.playground.feature.goodbyeworld.di.featureComponent
import eu.antoniolopez.playground.feature.goodbyeworld.presenter.GoodbyeNavigationPresenter
import eu.antoniolopez.playground.navigation.NavigationCommand
import eu.antoniolopez.playground.navigation.Navigator
import kotlinx.android.synthetic.main.goodbye_navigation_fragment.*
import org.kodein.di.generic.instance

class GoodbyeNavigationFragment
    : BaseFragment(), GoodbyeNavigationPresenter.View {

    companion object {
        fun newInstance() = GoodbyeNavigationFragment()
    }

    private val presenter: GoodbyeNavigationPresenter by featureComponent.instance()
    private val navigator: Navigator by featureComponent.instance()

    override fun onRequestLayoutResourceId(): Int = R.layout.goodbye_navigation_fragment

    override fun onViewReady(savedInstanceState: Bundle?) {
        presenter.onViewReady(this)
    }

    override fun onResume() {
        super.onResume()
        navigate.onClickDebounced { presenter.onNavigate() }
    }

    override fun navigateTo(command: NavigationCommand) {
        navigator.navigate(requireContext(), command)
        requireActivity().finish()
    }
}
