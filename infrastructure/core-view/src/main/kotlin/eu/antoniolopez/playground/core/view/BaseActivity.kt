package eu.antoniolopez.playground.core.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import eu.antoniolopez.playground.core.view.callback.OnBackPressed
import eu.antoniolopez.playground.core.view.extension.attach

abstract class BaseActivity : AppCompatActivity() {
    private val fragmentsCount: Int
        get() = supportFragmentManager.fragments.size

    private val hasFragments: Boolean
        get() = (fragmentsCount > 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.attach(R.id.content, onRequestFragment())
        }
    }

    abstract fun onRequestFragment(): Fragment

    override fun onBackPressed() {
        if (hasFragments) {
            var attended = false
            supportFragmentManager.fragments.forEach { childFragment ->
                if (childFragment is OnBackPressed) {
                    attended = childFragment.onBackPressed()
                }
            }
            if (!attended) super.onBackPressed()
        } else {
            super.onBackPressed()
        }
    }
}
