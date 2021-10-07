package com.example.assignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.assignment.pojo.ResponseModel
import com.example.assignment.repository.MyRepository
import kotlinx.coroutines.Dispatchers

class MyViewModel : ViewModel(){
    val repository= MyRepository()

  fun getData(data:Int): LiveData<ResponseModel> {
        return liveData (Dispatchers.IO) {
            val result = repository.getData(data).data
            emit(result!!)
        }
    }
}