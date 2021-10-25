package com.example.assignment.repository

import android.util.Log
import com.example.assignment.network.ApiClient
import com.example.assignment.network.Resource
import com.example.assignment.network.ResponseHandler
import com.example.assignment.network.RetrofitGenerator
import com.example.assignment.model.ResponseModel

class MyRepository {
    val api: ApiClient = RetrofitGenerator.getInstance().create(ApiClient::class.java)
    val responseHandler = ResponseHandler()

   suspend fun getData(data: Int, tags : String): Resource<ResponseModel> {
        val result = api.getData(
            "flickr.photos.search",
            "3e7cc266ae2b0e0d78e279ce8e361736",
            "json",
            1,
            1,
            tags,
            100,
            data
        )
       Log.d("res",result.toString())
       Log.d("res",tags.toString())
        return responseHandler.handleSuccess(result)
    }
}