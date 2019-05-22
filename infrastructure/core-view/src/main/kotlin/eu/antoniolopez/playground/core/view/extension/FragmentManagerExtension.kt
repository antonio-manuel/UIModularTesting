package eu.antoniolopez.playground.core.view.extension

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import arrow.core.Option
import kotlin.reflect.KClass

inline fun <reified T : Fragment> FragmentManager.ifIsNotAttached(block: (FragmentManager) -> Unit) {
    if (findByTag<T>().isEmpty()) block(this)
}

inline fun <reified T : Fragment> FragmentManager.findByTag(): Option<T> {
    val tag = getTag(T::class)
    return (this.findFragmentByTag(tag) as? T)?.let { Option.just(it) } ?: Option.empty()
}

inline fun <reified T : Fragment> FragmentManager.findByTagOrNull(): T? {
    val tag = getTag(T::class)
    return this.findFragmentByTag(tag) as? T
}

inline fun <reified T : Fragment> FragmentManager.findByTag(block: (T) -> Unit) {
    val tag = getTag(T::class)
    (this.findFragmentByTag(tag) as? T)?.let { block(it) }
}

fun FragmentManager.add(@IdRes placeHolder: Int, fragment: Fragment) {
    if (!fragment.isAdded) {
        val tag = getTag(fragment::class)
        beginTransaction()
            .add(placeHolder, fragment, tag)
            .commitNow()
    }
}

fun FragmentManager.attach(@IdRes placeHolder: Int, fragment: Fragment) {
    val tag = getTag(fragment::class)
    beginTransaction()
        .replace(placeHolder, fragment, tag)
        .commitNow()
}

fun getTag(type: KClass<*>) = type.java.name
