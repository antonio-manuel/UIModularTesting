package eu.antoniolopez.playground.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import eu.antoniolopez.playground.R
import eu.antoniolopez.playground.threading.bg
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
