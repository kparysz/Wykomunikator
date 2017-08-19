package unofficial.coderoid.wykop.newapp.utils

import android.app.Activity
import android.content.Context

interface NavigatorApi {
    fun openLoginWebActivity(context: Activity, requestCode: Int)
    fun openBrowser(context: Context, url: String?)
    fun openChatActivity(context: Context?, userName: String)
    fun openMessagesActivity(context: Context)
}
