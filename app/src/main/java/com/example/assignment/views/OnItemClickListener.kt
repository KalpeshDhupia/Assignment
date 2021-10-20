package com.example.assignment.views

import com.example.assignment.model.PhotoModel
import java.text.FieldPosition

interface OnItemClickListener {
    fun onItemClick(photoModel: PhotoModel,position: Int)
}