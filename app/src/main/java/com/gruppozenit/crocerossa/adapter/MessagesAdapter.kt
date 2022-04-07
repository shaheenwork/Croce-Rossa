package com.gruppozenit.crocerossa.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.TypefaceSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gruppozenit.crocerossa.R
import com.gruppozenit.crocerossa.model.messageListModels.MessageList
import com.gruppozenit.crocerossa.utils.Consts
import com.gruppozenit.crocerossa.utils.CustomTypefaceSpan
import com.gruppozenit.crocerossa.utils.PrefManager
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*


class MessagesAdapter(private var item_model: MutableList<MessageList>,
                      private val itemClick: ItemClick,
                      private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var prefManager: PrefManager? = null

    fun mRefresh(item_model: ArrayList<MessageList>) {
        this.item_model = item_model
    }

    interface ItemClick {
        fun itemClickEvent(item: MessageList)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = if (viewType == Consts.READ) {
            LayoutInflater.from(parent.context).inflate(R.layout.item_msg_read, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.item_msg_unread, parent, false)
        }
        prefManager = PrefManager.getInstance(context)
        return EntriesViewHolder(view, itemClick)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is EntriesViewHolder) {

            Picasso.get()
                    .load(item_model[position].userImage)
                     .placeholder(R.drawable.user_default)
                    .into(holder.profile_image)

            if (item_model[position].readAllAttachments) {
                holder.attachment_indicator.setImageResource(R.drawable.pin_grey)
            } else {
                holder.attachment_indicator.setImageResource(R.drawable.pin_orange)
            }

            if (item_model[position].attachmentCount == 0) {
                holder.attachment_indicator.visibility = View.GONE
            } else {
                holder.attachment_indicator.visibility = View.VISIBLE
            }


            if (position < item_model.size - 1 && item_model[position + 1].readMessage) {
                holder.msg_devider.visibility = View.VISIBLE
            } else {
                holder.msg_devider.visibility = View.GONE
            }


            if ( item_model[position].titolo == null){
                item_model[position].titolo=""
            }

            val bold = Typeface.createFromAsset(context.assets,"montserrat_semibold.ttf")

            val b: TypefaceSpan = CustomTypefaceSpan("", bold)

            val msg = SpannableString(item_model[position].titolo + "\n" + item_model[position].testo)
            msg.setSpan(b, 0, item_model[position].titolo.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)


            holder.date.text = item_model[position].messageDate
            holder.message.text = msg
            holder.name.text = item_model[position].user

        }
    }


    override fun getItemCount(): Int {
        return item_model!!.size
    }

    public fun getItems(): MutableList<MessageList> {
        return item_model
    }

    internal inner class EntriesViewHolder(itemView: View, private val itemClick: ItemClick) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val date: TextView = itemView.findViewById(R.id.txt_msgitem_date)
        val msg_devider: View = itemView.findViewById(R.id.msg_devider)
        val name: TextView = itemView.findViewById(R.id.txt_msgitem_name)
        val message: TextView = itemView.findViewById(R.id.txt_msgitem_title)
        private val viewItem: View = itemView.findViewById(R.id.view_item)

        val attachment_indicator: ImageView = itemView.findViewById(R.id.attachment)
        val profile_image: CircleImageView = itemView.findViewById(R.id.profile_image)

        init {
            viewItem.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            itemClick.itemClickEvent(item_model!![adapterPosition])
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {

        if (item_model[position].readMessage) {
            return Consts.READ
        } else {
            return Consts.UN_READ
        }
    }

}
