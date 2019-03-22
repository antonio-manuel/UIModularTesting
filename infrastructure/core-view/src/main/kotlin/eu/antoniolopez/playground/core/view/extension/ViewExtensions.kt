package eu.antoniolopez.playground.core.view.extension

import android.os.SystemClock
import android.view.View

const val DEBOUNCE_DELAY = 500L

inline fun View.onClickDebounced(crossinline block: () -> Unit) {
    var mLastClickTime = 0L
    //https://stackoverflow.com/questions/5608720/android-preventing-double-click-on-a-button/9950832
    setOnClickListener {
        if (SystemClock.elapsedRealtime() - mLastClickTime > DEBOUNCE_DELAY) {
            mLastClickTime = SystemClock.elapsedRealtime()
            block.invoke()
        }
    }
}
