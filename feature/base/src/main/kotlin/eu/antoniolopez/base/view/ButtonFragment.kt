package eu.antoniolopez.base.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eu.antoniolopez.base.R
import eu.antoniolopez.base.presenter.ButtonPresenter
import kotlinx.android.synthetic.main.fragment_button.*

class ButtonFragment
    : Fragment(), ButtonPresenter.View {

    companion object {
        fun newInstance() = ButtonFragment()
    }

    //private val presenter: ButtonPresenter by featureComponent.instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_button, container, false)
    }

    override fun onResume() {
        super.onResume()
        button.setOnClickListener {
            //presenter.onButton()
        }
    }

    override fun setText(text: String) {
        button.text = text
    }
}
