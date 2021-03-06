package eu.antoniolopez.playground.core.view.extension

import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import arrow.core.Option

const val DIALOG_TAG = "dialog"

fun Fragment.attach(@IdRes placeHolder: Int, fragment: Fragment) =
    childFragmentManager.attach(placeHolder, fragment)

inline fun <reified T : Fragment> Fragment.findById(@IdRes id: Int, block: (T) -> Unit) =
    childFragmentManager.findFragmentById(id)?.let { block(it as T) }

inline fun <reified T : Fragment> Fragment.findByTag(block: (T) -> Unit) =
    childFragmentManager.findByTag<T> { block(it) }

inline fun <reified T : Fragment> Fragment.findByTag(): Option<T> =
    childFragmentManager.findByTag()

inline fun <reified T : Fragment> Fragment.findByTagOrNull(): T? =
    childFragmentManager.findByTagOrNull()

fun Fragment.add(@IdRes placeHolder: Int, fragment: Fragment) =
    childFragmentManager.add(placeHolder, fragment)

fun <T : Fragment> T.withArguments(vararg arguments: Pair<String, Any?>): T {
    this.arguments = bundleOf(*arguments)
    return this
}
