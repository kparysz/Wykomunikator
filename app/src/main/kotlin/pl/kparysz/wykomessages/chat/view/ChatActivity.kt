package pl.kparysz.wykomessages.chat.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_chat.*
import pl.kparysz.wykomessages.R
import pl.kparysz.wykomessages.chat.adapter.ChatAdapter
import pl.kparysz.wykomessages.chat.presenter.ChatPresenter
import pl.kparysz.wykomessages.di.App
import pl.kparysz.wykomessages.models.dataclass.PrivateMessageDetail
import javax.inject.Inject


class ChatActivity : AppCompatActivity(), ChatView {

    companion object {
        const val USER_NAME_BUNDLE_KEY = "pl.kparysz.wykomessages.chat.user.name"
        @JvmStatic
        fun createIntent(context: Context?,
                         userName: String) = Intent(context, ChatActivity::class.java)
                .apply { putExtra(USER_NAME_BUNDLE_KEY, userName) }
    }

    @Inject
    lateinit var presenter: ChatPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).getInjector().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        presenter.setView(this)
        val userName = intent.getStringExtra(USER_NAME_BUNDLE_KEY)
        presenter.downloadChat(userName)
        showProgress()
        send.setOnClickListener { presenter.sendMessage(userName, message_body.text.toString()) }
    }

    override fun showChat(messages: List<PrivateMessageDetail>) {
        val adapter = ChatAdapter(this)
        chat_list.adapter = adapter
        chat_list.layoutManager = LinearLayoutManager(this)
        adapter.messageList += messages
        chat_list.scrollToPosition(messages.size - 1)
    }

    override fun showProgress() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_bar.visibility = View.INVISIBLE
    }

    override fun messageSent() {
        message_body.setText("")
        val userName = intent.getStringExtra(USER_NAME_BUNDLE_KEY)
        presenter.downloadChat(userName)
    }

    override fun showMessageSendError() {
        Snackbar.make(chat_list, getString(R.string.send_message_failed), Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.snackbar_retry), {})
                .setActionTextColor(ContextCompat.getColor(this, android.R.color.holo_red_light))
                .show()
    }

    override fun showError() {
        Snackbar.make(chat_list, getString(R.string.get_conversations_failed), Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.snackbar_retry), {})
                .setActionTextColor(ContextCompat.getColor(this, android.R.color.holo_red_light))
                .show()
    }

}
