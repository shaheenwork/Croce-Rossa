package com.gruppozenit.crocerossa.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gruppozenit.crocerossa.R
import com.gruppozenit.crocerossa.model.attachmentListModels.AttachmentList
import com.gruppozenit.crocerossa.utils.Consts
import com.gruppozenit.crocerossa.utils.PrefManager
import java.util.*


class AllegattiAdapter(private var item_model: MutableList<AttachmentList>,
                       private val itemClick: ItemClick,
                       private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var prefManager: PrefManager? = null

    fun mRefresh(item_model: ArrayList<AttachmentList>) {
        this.item_model = item_model
    }

    interface ItemClick {
        fun itemClickEvent(item: AttachmentList)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = if (viewType == Consts.READ) {
            LayoutInflater.from(parent.context).inflate(R.layout.item_allegati_read, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.item_allegati_unread, parent, false)
        }
        prefManager = PrefManager.getInstance(context)
        return EntriesViewHolder(view, itemClick)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is EntriesViewHolder) {

            if (item_model[position].type == Consts.FILE_TYPE_PDF) {
                holder.type_image.setImageResource(R.drawable.pdf)
            }
            else if (item_model[position].type == Consts.FILE_TYPE_IMAGE) {
                holder.type_image.setImageResource(R.drawable.pic)
            } else if (item_model[position].type == Consts.FILE_TYPE_VIDEO) {
                holder.type_image.setImageResource(R.drawable.play)
            } else if (item_model[position].type == Consts.FILE_TYPE_AUDIO) {
                holder.type_image.setImageResource(R.drawable.sound)
            } else if (item_model[position].type == Consts.FILE_TYPE_LINK) {
                holder.type_image.setImageResource(R.drawable.link)
            }



            if (position < item_model.size - 1 && item_model[position + 1].fileReadStatus) {
                holder.msg_devider.visibility = View.VISIBLE
            } else {
                holder.msg_devider.visibility = View.GONE
            }

            holder.fileName.text = item_model[position].title
            holder.user_name.text = item_model[position].adminName
            holder.date.text =item_model[position].date

        }
    }


    override fun getItemCount(): Int {
        return item_model!!.size
    }

    public fun getItems(): MutableList<AttachmentList> {
        return item_model
    }

    internal inner class EntriesViewHolder(itemView: View, private val itemClick: ItemClick) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val fileName: TextView = itemView.findViewById(R.id.txt_allegati_name)
        val msg_devider: View = itemView.findViewById(R.id.msg_devider)
        val name: TextView = itemView.findViewById(R.id.txt_allegati_name)
        val user_name: TextView = itemView.findViewById(R.id.txt_user_name)
        val type_image: ImageView = itemView.findViewById(R.id.type_image)
        val date: TextView = itemView.findViewById(R.id.txt_date)
        private val viewItem: View = itemView.findViewById(R.id.view_item)

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

        if (item_model[position].fileReadStatus) {
            return Consts.READ
        } else {
            return Consts.UN_READ
        }
    }

}
