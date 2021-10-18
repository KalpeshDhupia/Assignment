package com.example.assignment.adapter


import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.assignment.model.PhotoModel


@BindingAdapter("imageUrl")
fun ImageView.imageUrl(photoModel: PhotoModel) {
    Glide.with(this.context)
        .load("https://live.staticflickr.com/${photoModel.server}/${photoModel.id}_${photoModel.secret}_w.jpg")
        .into(this)

}