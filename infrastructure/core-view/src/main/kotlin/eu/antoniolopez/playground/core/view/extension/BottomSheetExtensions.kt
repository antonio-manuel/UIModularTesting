package eu.antoniolopez.playground.core.view.extension

import android.support.annotation.NonNull
import android.support.design.widget.BottomSheetBehavior
import android.view.View

inline fun <reified T : View> BottomSheetBehavior<T>.onStateChanged(crossinline onStageChanged: (Int) -> Unit) {
    setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onStateChanged(@NonNull bottomSheet: View, newState: Int) {
            onStageChanged(newState)
        }

        override fun onSlide(@NonNull bottomSheet: View, slideOffset: Float) {
            //EMPTY
        }
    })
}

inline fun <reified T : View> BottomSheetBehavior<T>.onStateChanged(
    crossinline onStageChanged: (Int) -> Unit,
    crossinline onSlide: (Float) -> Unit
) {
    setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onStateChanged(@NonNull bottomSheet: View, newState: Int) {
            onStageChanged(newState)
        }

        override fun onSlide(@NonNull bottomSheet: View, slideOffset: Float) {
            onSlide(slideOffset)
        }
    })
}
