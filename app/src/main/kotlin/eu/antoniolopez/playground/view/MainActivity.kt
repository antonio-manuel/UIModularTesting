package eu.antoniolopez.playground.view

import eu.antoniolopez.playground.core.view.BaseActivity

class MainActivity : BaseActivity() {
    override fun onRequestFragment() = MainFragment.newInstance()
}

