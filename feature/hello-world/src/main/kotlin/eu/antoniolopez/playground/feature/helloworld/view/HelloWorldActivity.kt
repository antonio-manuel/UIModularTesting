package eu.antoniolopez.playground.feature.helloworld.view

import eu.antoniolopez.playground.core.view.BaseActivity

class HelloWorldActivity : BaseActivity() {
    override fun onRequestFragment() = HelloWorldFragment.newInstance()
}
