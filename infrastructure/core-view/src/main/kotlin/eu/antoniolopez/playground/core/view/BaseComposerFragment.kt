package eu.antoniolopez.playground.core.view

import android.os.Bundle
import android.view.View

abstract class BaseComposerFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            onCompose()
        }
        onSetupListeners()
        onViewReady(savedInstanceState)
    }

    override fun onViewReady(savedInstanceState: Bundle?) {}

    abstract fun onCompose()
}
