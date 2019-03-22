package eu.antoniolopez.playground.core.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eu.antoniolopez.playground.core.view.lifecycle.LifecycleFragment
import java.io.Serializable

abstract class BaseFragment : LifecycleFragment() {

    companion object {
        const val EXTRA_VALUE = "extra:value"
    }

    private val layoutResourceId by lazy {
        onRequestLayoutResourceId()
    }

    private var onFragmentReadyListener: (() -> Unit)? = null
    private var isReady = false

    protected val isViewReady: Boolean
        get() = isReady

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(layoutResourceId, container, false)

    @LayoutRes
    abstract fun onRequestLayoutResourceId(): Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onSetupListeners()
        onViewReady(savedInstanceState)
        isReady = true
        onFragmentReadyListener?.invoke()
    }

    fun setOnFragmentReadyListener(listener: () -> Unit) {
        this.onFragmentReadyListener = listener
    }

    open fun onSetupListeners() {}

    open fun onViewReady(savedInstanceState: Bundle?) {}

    fun renderDialog(fragment: DialogFragment, idDialog: String) {
        val ft = childFragmentManager.beginTransaction()
        val prev = childFragmentManager.findFragmentByTag(idDialog)
        if (prev != null) {
            ft.remove(prev)
        }
        ft.addToBackStack(null)
        fragment.show(ft, idDialog)
    }

    fun finishWithResult(value: Serializable) {
        val arguments = Intent()
        arguments.putExtra(EXTRA_VALUE, value)
        activity?.setResult(Activity.RESULT_OK, arguments)
        activity?.finish()
    }
}
