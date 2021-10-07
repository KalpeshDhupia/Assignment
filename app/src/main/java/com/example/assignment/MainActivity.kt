package com.example.assignment

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment.adapter.PictureAdapter
import com.example.assignment.pojo.PhotoModel
import com.example.assignment.viewmodel.MyViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var myViewModel: MyViewModel
    lateinit var pictureAdapter: PictureAdapter
    val picList:MutableList<PhotoModel> = mutableListOf()
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myViewModel= ViewModelProviders.of(this).get(MyViewModel::class.java);
        pictureAdapter= PictureAdapter(picList)
        rv_pic.layoutManager= LinearLayoutManager(this)
        myViewModel.getData(1).observe(this, Observer {
            picList.clear()
            it.photos?.let { it1 -> picList.addAll(it1.photo as List<PhotoModel>) }
        })
        rv_pic.adapter=pictureAdapter
        pictureAdapter.notifyDataSetChanged()
    }
}