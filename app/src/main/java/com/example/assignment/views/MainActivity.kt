package com.example.assignment.views

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.assignment.R
import com.example.assignment.adapter.PictureAdapter
import com.example.assignment.databinding.ActivityMainBinding
import com.example.assignment.model.PhotoModel
import com.example.assignment.viewmodel.MyViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log

class MainActivity : AppCompatActivity(), OnItemClickListener,
    SwipeRefreshLayout.OnRefreshListener {
    private var layoutManager: GridLayoutManager? = null
    lateinit var myViewModel: MyViewModel
    lateinit var pictureAdapter: PictureAdapter
    lateinit var binding: ActivityMainBinding
    val picList: ArrayList<PhotoModel> = ArrayList()
    var photoData: ArrayList<PhotoModel> = ArrayList()
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    var tag: String = "kitten"

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        swipeRefreshLayout = findViewById(R.id.swipeContainer)
        myViewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)

        layoutManager = GridLayoutManager(this, 1)
        rv_pic.layoutManager = layoutManager
        //  pictureAdapter = PictureAdapter(picList,this)
        //  rv_pic.layoutManager = LinearLayoutManager(this)
        pictureAdapter = PictureAdapter(picList, this, layoutManager)
        /*floatingActionButton.setOnClickListener {
            etTag.visibility = View.VISIBLE
            tag = etTag.text.toString()
            floatingActionButton.setOnClickListener {
                etTag.visibility = View.GONE
            }
        }*/



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

            pictureAdapter.notifyDataSetChanged()

        }

        myViewModel.getData(20, tag).observe(this, Observer {
            picList.clear()
            it.photos?.let { it1 -> picList.addAll(it1.photo as List<PhotoModel>) }
            shimmerFrameLayout.stopShimmer()
            shimmerFrameLayout.visibility = View.GONE
            rv_pic.visibility = View.VISIBLE
            pictureAdapter.notifyDataSetChanged()
            swipeRefreshLayout.isRefreshing = false
        })
        rv_pic.adapter = pictureAdapter
        rv_pic.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 5) {
                    myViewModel.getData(dy + 10, tag)
                    Log.d("msg", dy.toString())
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val menuItem: MenuItem = menu!!.findItem(R.id.action_search)
      //  val searchView: androidx.appcompat.widget.SearchView = menuItem.actionView as androidx.appcompat.widget.SearchView
        val searchView = menuItem.actionView as? SearchView
        searchView?.queryHint = "Tyre here to search"

       /* searchView?.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener,
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null && query.length >= 3) {
                    myViewModel.getData(20,query)

                }
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return false
            }

        })*/

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val searchview = item.actionView as SearchView

        searchview.queryHint = "Type to Search"


        searchview.setOnQueryTextListener(object : OnQueryTextListener,
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null && query.length >= 3) {
                    myViewModel.getData(20,query)

                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })
        return super.onOptionsItemSelected(item)
    }



    override fun onResume() {
        super.onResume()
        shimmerFrameLayout.startShimmer()
    }

    override fun onPause() {
        shimmerFrameLayout.stopShimmer()
        super.onPause()
    }

    override fun onItemClick(photoModel: PhotoModel, position: Int) {
        for (i in picList.iterator()) {

            //    photoModel.list?.add ("https://live.staticflickr.com/${photoModel.server}/${photoModel.id}_${photoModel.secret}_w.jpg")
            //   photoData.add(photoModel)
        }


        // photoModel.list = photoData
        val intent = Intent(this, ZoomActivity::class.java)

        Log.d("", "onItemClick: " + position)

        intent.putExtra("server", photoModel.server)
        intent.putExtra("secret", photoModel.secret)
        intent.putExtra("id", photoModel.id)
        intent.putExtra("position", position)
        intent.putParcelableArrayListExtra("url", picList)
        startActivity(intent)
    }

    override fun onRefresh() {
        swipeRefreshLayout.setOnRefreshListener {
            myViewModel.getData(20, tag)
            refreshList()
        }

    }

    private fun refreshList() {
        swipeRefreshLayout.isRefreshing = false
    }
}
