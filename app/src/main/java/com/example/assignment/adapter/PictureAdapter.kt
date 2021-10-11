package com.example.assignment.adapter

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignment.R
import com.example.assignment.model.PhotoModel
import com.example.assignment.views.onItemClickListener
import kotlinx.android.synthetic.main.pic_item.view.*

class PictureAdapter(
    val photoList: List<PhotoModel>,
    private val listener: onItemClickListener,
    private val layoutManager: GridLayoutManager? = null
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ViewType {
        GRID,
        LIST
    }

    class PictureViewHolder(view: View) : RecyclerView.ViewHolder(view)
    class PictureGridViewHolder(view: View) : RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.LIST.ordinal -> PictureViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.pic_item, parent, false)
            )
            else -> PictureGridViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.pic_griditem, parent, false)
            )

        }
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (layoutManager?.spanCount == 1) ViewType.LIST.ordinal
        else ViewType.GRID.ordinal
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val result = photoList[position]

        Glide.with(holder.itemView.ivPic)
            .load("https://live.staticflickr.com/${result.server}/${result.id}_${result.secret}_w.jpg")
            .into(holder.itemView.ivPic);
        holder.itemView.setOnClickListener {
            listener.onItemClick(photoList[position])
        }
    }


}