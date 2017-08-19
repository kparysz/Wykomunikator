package pl.kparysz.wykomessages.chat.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_chat.*
import pl.kparysz.wykomessages.R
import pl.kparysz.wykomessages.chat.adapter.MessageAdapter
import pl.kparysz.wykomessages.chat.presenter.ChatPresenter
import pl.kparysz.wykomessages.di.App
import pl.kparysz.wykomessages.models.dataclass.PrivateMessageDetail
import javax.inject.Inject

class ChatActivity : AppCompatActivity(), ChatView {

    companion object {
        const val USER_NAME_BUNDLE_KEY = "pl.kparysz.wykomessages.chat.user.name"
        @JvmStatic fun createIntent(context: Context?,
                                    userName: String) = Intent(context, ChatActivity::class.java)
                .apply { putExtra(USER_NAME_BUNDLE_KEY, userName) }
    }

    @Inject
    lateinit var presenter: ChatPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).getInjector()?.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        presenter.setView(this)
        val userName = intent.getStringExtra(USER_NAME_BUNDLE_KEY)
        presenter.downloadChat(userName)
    }

    override fun showChat(messages: List<PrivateMessageDetail>) {
        val adapter = MessageAdapter()
        chat_list.adapter = adapter
        chat_list.layoutManager = LinearLayoutManager(this)
        adapter.messageList += messages
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
