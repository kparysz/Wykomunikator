package pl.kparysz.wykomessages.messages.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_messages.*
import pl.kparysz.wykomessages.R
import pl.kparysz.wykomessages.di.App
import pl.kparysz.wykomessages.messages.adapters.ConversationsAdapter
import pl.kparysz.wykomessages.messages.presenter.MessagesPresenter
import pl.kparysz.wykomessages.models.dataclass.ConversationDetail
import javax.inject.Inject

class MessagesActivity : AppCompatActivity(), MessagesView {
    companion object {
        @JvmStatic fun createIntent(context: Context) = Intent(context, MessagesActivity::class.java)
    }

    @Inject
    lateinit var presenter: MessagesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).getInjector()?.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messages)
        presenter.setView(this)
    }

    override fun getContext() = this

    override fun showMessages(messages: List<ConversationDetail>) {
        val adapter = ConversationsAdapter(this)
        messages_list.adapter = adapter
        messages_list.layoutManager = LinearLayoutManager(this)
        adapter.conversationList += messages
    }

    override fun showError() {

    }
}
