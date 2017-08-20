package pl.kparysz.wykomessages.chat.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import pl.kparysz.wykomessages.R
import pl.kparysz.wykomessages.models.dataclass.PrivateMessageDetail

class ChatAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_MESSAGE_SENT = 1
    private val VIEW_TYPE_MESSAGE_RECEIVED = 2

    var messageList: List<PrivateMessageDetail> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun getItemViewType(position: Int): Int {
        val message = messageList[position]
        return if (message.isOwner) {
            VIEW_TYPE_MESSAGE_SENT
        } else {
            VIEW_TYPE_MESSAGE_RECEIVED
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        val view: View
        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.chat_message_sent, parent, false)
            return SentMessageHolder(view)
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.chat_message_received, parent, false)
            return ReceivedMessageHolder(view)
        }
        return null
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messageList[position]
        when (holder.itemViewType) {
            VIEW_TYPE_MESSAGE_SENT -> (holder as SentMessageHolder).bind(message)
            VIEW_TYPE_MESSAGE_RECEIVED -> (holder as ReceivedMessageHolder).bind(context, message)
        }
    }

    private class SentMessageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var messageText: TextView = itemView.findViewById(R.id.text_message_body) as TextView
        var timeText: TextView = itemView.findViewById(R.id.text_message_time) as TextView

        internal fun bind(message: PrivateMessageDetail) {
            messageText.text = message.body
            timeText.text = (message.date)
        }
    }

    private class ReceivedMessageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var messageText: TextView = itemView.findViewById(R.id.text_message_body) as TextView
        var timeText: TextView = itemView.findViewById(R.id.text_message_time) as TextView
        var nameText: TextView = itemView.findViewById(R.id.text_message_author) as TextView
        var profileImage: ImageView = itemView.findViewById(R.id.image_message_profile) as ImageView

        internal fun bind(context: Context, message: PrivateMessageDetail) {
            messageText.text = message.body
            timeText.text = (message.date)
            nameText.text = message.author
            Glide.with(context)
                    .load(message.authorAvatarUrl)
                    .into(profileImage)
        }
    }
}