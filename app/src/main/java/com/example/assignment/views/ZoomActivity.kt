package com.example.assignment.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.assignment.R
import com.example.assignment.adapter.ViewPagerAdapter
import com.example.assignment.databinding.ActivityZoomBinding
import com.example.assignment.model.PhotoModel

class ZoomActivity : AppCompatActivity() {
    lateinit var imageUrl: String

    private lateinit var viewPager2: ViewPager2

    lateinit var zoomBinding: ActivityZoomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        zoomBinding = DataBindingUtil.setContentView(this, R.layout.activity_zoom)

        val server = intent.getStringExtra("server")
        val secret = intent.getStringExtra("secret")
        val id = intent.getStringExtra("id")
        val position = intent.getIntExtra("position",1)
        imageUrl = "https://live.staticflickr.com/${server}/${id}_${secret}_w.jpg"

        val photolist   =  intent.getParcelableArrayListExtra<PhotoModel>("url")
        Log.d("tag", "onCreate: "+ photolist)

       /* Glide.with(imageView)
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
            image.startAnimation(animZoomOut)*/

        val adapter = ViewPagerAdapter(photolist as ArrayList<PhotoModel>)
        viewPager2 = findViewById(R.id.view_pager2)
        viewPager2.adapter = adapter
        viewPager2.currentItem = position

    }




    }

