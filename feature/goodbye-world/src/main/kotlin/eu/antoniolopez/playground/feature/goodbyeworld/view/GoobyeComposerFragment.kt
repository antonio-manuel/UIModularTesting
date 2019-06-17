package eu.antoniolopez.playground.feature.goodbyeworld.view

import eu.antoniolopez.playground.core.view.BaseComposerFragment
import eu.antoniolopez.playground.core.view.extension.attach
import eu.antoniolopez.playground.feature.goodbyeworld.R

class GoobyeComposerFragment : BaseComposerFragment() {

    companion object {
        fun newInstance() = GoobyeComposerFragment()
    }

    override fun onRequestLayoutResourceId(): Int = R.layout.goodbye_composer

    override fun onCompose() {
        attach(R.id.action, GoodbyeActionFragment.newInstance())
        attach(R.id.navigation, GoodbyeNavigationFragment.newInstance())
    }
}
