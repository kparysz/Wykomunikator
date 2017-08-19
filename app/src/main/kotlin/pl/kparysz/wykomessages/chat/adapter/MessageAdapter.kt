package pl.kparysz.wykomessages.chat.adapter

import android.support.v4.widget.Space
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import pl.kparysz.wykomessages.R
import pl.kparysz.wykomessages.models.dataclass.PrivateMessageDetail

class MessageAdapter : RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    var messageList: List<PrivateMessageDetail> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MessageAdapter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.message_view, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MessageAdapter.ViewHolder, i: Int) {
        viewHolder.message.text = messageList[i].body
        viewHolder.author.text = messageList[i].author
        viewHolder.date.text = messageList[i].date
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val root: LinearLayout = view.findViewById(R.id.message_parent) as LinearLayout
        val message: TextView = view.findViewById(R.id.message) as TextView
        val author: TextView = view.findViewById(R.id.author_name) as TextView
        val date: TextView = view.findViewById(R.id.date) as TextView
        val space: Space = view.findViewById(R.id.space) as Space

    }

}