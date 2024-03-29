package com.example.assignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.assignment.model.ResponseModel
import com.example.assignment.repository.MyRepository
import kotlinx.coroutines.Dispatchers

class MyViewModel : ViewModel() {
    val repository = MyRepository()

    fun getData(data: Int, tags: String): LiveData<ResponseModel> {
        return liveData(Dispatchers.IO) {
            val result = repository.getData(data, tags).data
            emit(result!!)

        }
    }
}