package pl.kparysz.wykomessages.ui

import android.content.Context
import android.graphics.Typeface
import java.util.*

object TypefaceProvider {

    private val nameToTypeface = HashMap<String, Typeface>()

    fun regular(context: Context): Typeface = getFont(context, "fonts/Fabrica.otf")

    private fun getFont(context: Context, filename: String): Typeface {
        if (!nameToTypeface.containsKey(filename)) {
            nameToTypeface.put(filename, Typeface.createFromAsset(context.assets, filename))
        }
        return nameToTypeface[filename]!!
    }
}