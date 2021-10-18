package com.example.assignment.views

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R
import com.example.assignment.adapter.PictureAdapter
import com.example.assignment.databinding.ActivityMainBinding
import com.example.assignment.model.PhotoModel
import com.example.assignment.viewmodel.MyViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnItemClickListener {
    private var layoutManager: GridLayoutManager? = null
    lateinit var myViewModel: MyViewModel
    lateinit var pictureAdapter: PictureAdapter
    lateinit var binding: ActivityMainBinding
    val picList: MutableList<PhotoModel> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        myViewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)

        layoutManager = GridLayoutManager(this, 1)
        rv_pic.layoutManager = layoutManager
      //  pictureAdapter = PictureAdapter(picList,this)
      //  rv_pic.layoutManager = LinearLayoutManager(this)
        pictureAdapter = PictureAdapter(picList,this,layoutManager)
        btn_Change.setOnClickListener {
            if (layoutManager?.spanCount == 1) {
                layoutManager?.spanCount = 3
                pictureAdapter.viewTypeData = "Grid"
               // item.title = "list"
            } else {
                pictureAdapter.viewTypeData = "List"
                layoutManager?.spanCount = 1
                //item.title = "grid"
            }

            pictureAdapter?.notifyDataSetChanged()
        }
        myViewModel.getData(20).observe(this, Observer {
            picList.clear()
            it.photos?.let { it1 -> picList.addAll(it1.photo as List<PhotoModel>) }
            shimmerFrameLayout.stopShimmer()
            shimmerFrameLayout.visibility = View.GONE
            rv_pic.visibility = View.VISIBLE
            pictureAdapter.notifyDataSetChanged()
        })
        rv_pic.adapter = pictureAdapter
        rv_pic.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 5) {
                    myViewModel.getData(dy + 10)
                    Log.d("msg", dy.toString())
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        shimmerFrameLayout.startShimmer()
    }

    override fun onPause() {
        shimmerFrameLayout.stopShimmer()
        super.onPause()
    }

    override fun onItemClick(photoModel: PhotoModel) {
        val intent = Intent(this,ZoomActivity::class.java)
        intent.putExtra("server", photoModel.server)
        intent.putExtra("secret", photoModel.secret)
        intent.putExtra("id", photoModel.id)
        startActivity(intent)
    }
}
