package eu.antoniolopez.playground.feature.goodbyeworld.view

import eu.antoniolopez.playground.core.view.BaseActivity

class GoodbyeWorldActivity : BaseActivity() {
    override fun onRequestFragment() = GoodbyeWorldFragment.newInstance()
}
