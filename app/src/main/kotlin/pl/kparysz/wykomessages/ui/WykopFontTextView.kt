package pl.kparysz.wykomessages.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView

open class WykopFontTextView(context: Context, attributeSet: AttributeSet) : TextView(context, attributeSet) {

    override fun onDetachedFromWindow() {
        // This is a fix for leaking TextView and objects related to it via ViewTreeObserver
        // See this issue for details https://code.google.com/p/android/issues/detail?id=171830
        viewTreeObserver.removeOnPreDrawListener(this)
        super.onDetachedFromWindow()
    }

    init {
        isLongClickable = false
        if (isInEditMode) {

        }
        typeface = TypefaceProvider.regular(context)
    }
}