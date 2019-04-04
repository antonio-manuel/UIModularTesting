package eu.antoniolopez.playground.view

import android.app.Activity
import android.os.Bundle
import eu.antoniolopez.playground.R

class SimpleAppActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.simple_app_activity)
    }
}
