package eu.antoniolopez.playground.core.view.extension

import android.app.Activity
import android.app.ActivityManager
import android.app.ActivityManager.RunningAppProcessInfo
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

fun Context.convertDpToPixel(value: Float): Float = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    value,
    resources.displayMetrics
)

fun Context.convertDpToPixel(value: Int): Int = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    value.toFloat(),
    resources.displayMetrics
).toInt()

fun Context.hideKeyboardFrom(view: View?) {
    view ?: return
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context?.styledAttributes(
    attrs: AttributeSet?,
    styleable: IntArray,
    block: (TypedArray) -> Unit
) = this?.let { context ->
    attrs?.let { attrs ->
        val attributes = context.theme.obtainStyledAttributes(attrs, styleable, 0, 0)
        block(attributes)
        attributes.recycle()
    }
}

suspend fun Context.isInForeground() = suspendCoroutine<Boolean> { continuation ->
    val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val appProcesses = activityManager.runningAppProcesses ?: emptyList()
    if (appProcesses.isEmpty()) {
        continuation.resume(false)
    } else {
        var found = false
        val packageName = packageName
        for (appProcess in appProcesses) {
            if (appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcess.processName == packageName) {
                found = true
            }
        }
        continuation.resume(found)
    }
}

fun Context.getStringIdByName(stringIdName: String): Int {
    val packageName = packageName
    return resources.getIdentifier(stringIdName, "string", packageName)
}

fun Context.getStringByName(stringIdName: String): String {
    return if (getStringIdByName(stringIdName) == 0) {
        stringIdName
    } else {
        getString(getStringIdByName(stringIdName))
    }
}
