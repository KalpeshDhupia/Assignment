package com.example.assignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R
import com.example.assignment.databinding.PicGriditemBinding
import com.example.assignment.databinding.PicItemBinding
import com.example.assignment.model.PhotoModel
import com.example.assignment.views.OnItemClickListener


class PictureAdapter(
    val photoList: List<PhotoModel>,
    private val listener: OnItemClickListener,
    private val layoutManager: GridLayoutManager? = null
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var viewTypeData: String


    class PictureViewHolder(
        private val picItemBinding: PicItemBinding,
        private val itemClickListener: OnItemClickListener
    ) :
        RecyclerView.ViewHolder(picItemBinding.root) {
        fun onBind(photoModel: PhotoModel) {
            picItemBinding.photoModel = photoModel
            picItemBinding.itemClickListener = itemClickListener
        }
    }

    class PictureGridViewHolder(
        private val gridItemBinding: PicGriditemBinding,
        private val itemClickListener: OnItemClickListener
    ) :
        RecyclerView.ViewHolder(gridItemBinding.root) {
        fun onBind(photoModel: PhotoModel) {
            gridItemBinding.photoModel = photoModel
            gridItemBinding.itemClickListener = itemClickListener
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        viewTypeData = if (viewType == 1) {
            "List"
        } else {
            "Grid"
        }
        return when (viewType) {
            1 -> PictureViewHolder(

                DataBindingUtil.inflate(layoutInflater, R.layout.pic_item, parent, false), listener
            )
            else -> PictureGridViewHolder(
                DataBindingUtil.inflate(layoutInflater, R.layout.pic_griditem, parent, false),
                listener
            )

        }
        /* val layoutInflater = LayoutInflater.from(parent.context)
         val picItemBinding: PicItemBinding =
             DataBindingUtil.inflate(layoutInflater, R.layout.pic_item, parent, false)
         return PictureViewHolder(picItemBinding)*/

    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (layoutManager?.spanCount == 1) 1
        else 2
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val result = photoList[position]

        if (viewTypeData == "List") {
            (holder as PictureViewHolder).onBind(result)
        } else {
            (holder as PictureGridViewHolder).onBind(result)
        }

    }


}