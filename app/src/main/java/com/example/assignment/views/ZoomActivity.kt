package com.example.assignment.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.assignment.R
import kotlinx.android.synthetic.main.activity_zoom.*
import kotlinx.android.synthetic.main.pic_item.view.*

class ZoomActivity : AppCompatActivity() {
    lateinit var imageUrl: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zoom)
        val server = intent.getStringExtra("server")
        val secret = intent.getStringExtra("secret")
        val id = intent.getStringExtra("id")
        imageUrl = "https://live.staticflickr.com/${server}/${id}_${secret}_w.jpg"

        Glide.with(imageView)
            .load(imageUrl)
            .into(imageView)
        val image: ImageView = findViewById(R.id.imageView)

        zoomInButton.setOnClickListener() {
            val animZoomIn = AnimationUtils.loadAnimation(
                this,
                R.anim.zoom_in
            )
            image.startAnimation(animZoomIn)
        }

        zoomOutButton.setOnClickListener() {
            val animZoomOut = AnimationUtils.loadAnimation(
                this,
                R.anim.zoom_out
            )
            image.startAnimation(animZoomOut)
        }


    }

}