package com.gruppozenit.crocerossa.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gruppozenit.crocerossa.R
import com.gruppozenit.crocerossa.model.messageDetailsModels.MessageAttachment
import com.gruppozenit.crocerossa.utils.Consts
import com.gruppozenit.crocerossa.utils.PrefManager

import java.util.ArrayList


class FilesAdapter(private var item_model: MutableList<MessageAttachment>,
                   private val itemClick: ItemClick,
                   private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var prefManager: PrefManager? = null

    fun mRefresh(item_model: ArrayList<MessageAttachment>) {
        this.item_model = item_model
    }

    interface ItemClick {
        fun itemClickEvent(item: MessageAttachment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_file_new, parent, false)
        prefManager = PrefManager.getInstance(context)
        return EntriesViewHolder(view, itemClick)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is EntriesViewHolder) {

            if (item_model[position].type == Consts.FILE_TYPE_PDF) {
                holder.file_type.setImageResource(R.drawable.pdf)
                holder.fileName.text = "Apri PDF"
            } else if (item_model[position].type == Consts.FILE_TYPE_IMAGE) {
                holder.file_type.setImageResource(R.drawable.pic)
                holder.fileName.text = "Apri Immagine"
            } else if (item_model[position].type == Consts.FILE_TYPE_VIDEO) {
                holder.file_type.setImageResource(R.drawable.play)
                holder.fileName.text = "Apri Video"
            } else if (item_model[position].type == Consts.FILE_TYPE_AUDIO) {
                holder.file_type.setImageResource(R.drawable.sound)
                holder.fileName.text = "Apri Audio"
            } else if (item_model[position].type == Consts.FILE_TYPE_LINK) {
                holder.file_type.setImageResource(R.drawable.link)
                holder.fileName.text = "Apri Link"
            }


        }
    }


    override fun getItemCount(): Int {
        return item_model!!.size
    }

    public fun getItems(): MutableList<MessageAttachment> {
        return item_model
    }

    internal inner class EntriesViewHolder(itemView: View, private val itemClick: ItemClick) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val fileName: TextView = itemView.findViewById(R.id.txt_filename)
        private val viewItem: LinearLayout = itemView.findViewById(R.id.view_file)
        val file_type: ImageView = itemView.findViewById(R.id.img_file_type)

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
        return position
    }

}
