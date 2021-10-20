package com.example.assignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignment.R
import com.example.assignment.model.PhotoModel

class ViewPagerAdapter(private val imageUrlList: ArrayList<PhotoModel>) :
    RecyclerView.Adapter<ViewPagerAdapter.PagerVH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH =
        PagerVH(LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false))

    override fun onBindViewHolder(holder: PagerVH, position: Int) {
        val image = imageUrlList[position]
        holder.bind(image)
    }

    override fun getItemCount(): Int {
        return imageUrlList.size
    }


    class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivSliderImage = itemView.findViewById<ImageView>(R.id.ivSliderImage)

        fun bind(photoModel: PhotoModel) {
            Glide.with(ivSliderImage)
                .load("https://live.staticflickr.com/${photoModel.server}/${photoModel.id}_${photoModel.secret}_w.jpg")
                .into(ivSliderImage)
        }
    }
}


