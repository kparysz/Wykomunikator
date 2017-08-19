package unofficial.coderoid.wykop.newapp.utils

import android.app.Activity
import android.app.Application
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import pl.kparysz.wykomessages.chat.view.ChatActivity
import pl.kparysz.wykomessages.login.view.LoginWebActivity
import pl.kparysz.wykomessages.messages.view.MessagesActivity

class Navigator(val application: Application) : NavigatorApi {

    override fun openLoginWebActivity(context: Activity, requestCode: Int) {
        val intent = LoginWebActivity.createIntent(context)
        context.startActivityForResult(intent, requestCode)
    }

    override fun openMessagesActivity(context: Context) {
        val intent = MessagesActivity.createIntent(context)
        context?.startActivity(intent)
    }

    override fun openChatActivity(context: Context?, userName: String) {
        val intent = ChatActivity.createIntent(context, userName)
        context?.startActivity(intent)
    }

    override fun openBrowser(context: Context, url: String?) {
        val intent = Intent(Intent.ACTION_VIEW)
        if (url == null) {
            return
        }
        intent.data = Uri.parse(Uri.decode(url))
        try {
            context.startActivity(intent)
        } catch (activityNotFoundException: ActivityNotFoundException) {
            //can't do much about that
        }
    }
}
