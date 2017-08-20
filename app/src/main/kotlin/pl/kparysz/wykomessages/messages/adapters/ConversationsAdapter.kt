package pl.kparysz.wykomessages.messages.adapters

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.conversation_view.view.*
import pl.kparysz.wykomessages.R
import pl.kparysz.wykomessages.models.dataclass.ConversationDetail

class ConversationsAdapter(val context: Context) : RecyclerView.Adapter<ConversationsAdapter.MessagesViewHolder>() {

    var conversationList: List<ConversationDetail> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = conversationList.size

    override fun onBindViewHolder(holder: MessagesViewHolder, position: Int) {
        val conversation = conversationList[position]
        holder.author.text = conversation.authorName
        holder.date.text = conversation.lastUpdate
        Glide.with(context)
                .load(conversation.avatarUrl)
                .into(holder.image)
        setAuthorColor(conversation.authorGroup)
        setImageBorder(conversation.authorSex, holder.image)
        setMessageStatus(conversation.status, holder.status)
        holder.root.setOnClickListener { conversation.onClickAction.invoke() }
    }

    private fun setMessageStatus(status: String, statusView: TextView) {
        if (status == "read") {
            statusView.text = context.getString(R.string.message_read_status)
        } else {
            statusView.text = context.getString(R.string.message_new_status)
        }
    }

    private fun setAuthorColor(authorGroup: Int): Int =
            when (authorGroup) {
                0 -> ContextCompat.getColor(context, R.color.green_user)
                1 -> ContextCompat.getColor(context, R.color.orange_user)
                else -> ContextCompat.getColor(context, R.color.red_user)
            }

    private fun setImageBorder(authorSex: String, imageView: CircleImageView) {
        when (authorSex) {
            "female" -> imageView.borderColor = ContextCompat.getColor(context, R.color.colorAccent)
            "male" -> imageView.borderColor = ContextCompat.getColor(context, R.color.main_tab_unselected)
            else -> return
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MessagesViewHolder {
        val itemView = LayoutInflater
                .from(viewGroup.context)
                .inflate(R.layout.conversation_view, viewGroup, false)

        return MessagesViewHolder(itemView)
    }

    class MessagesViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val root = v.message_parent
        val author = v.user_name
        val status = v.status
        val image = v.user_avatar
        val date = v.last_update
    }

}