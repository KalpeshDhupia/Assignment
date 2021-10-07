package com.example.assignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignment.R
import com.example.assignment.model.PhotoModel
import kotlinx.android.synthetic.main.pic_item.view.*

class PictureAdapter(val photoList: List<PhotoModel>) :
    RecyclerView.Adapter<PictureAdapter.PictureViewHolder>() {


    class PictureViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        return PictureViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.pic_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        val result = photoList[position]
        Glide.with(holder.itemView.ivPic)
            .load("https://live.staticflickr.com/${result.server}/${result.id}_${result.secret}_w.jpg")
            .into(holder.itemView.ivPic);

    }

    override fun getItemCount(): Int {
        return photoList.size
    }

}