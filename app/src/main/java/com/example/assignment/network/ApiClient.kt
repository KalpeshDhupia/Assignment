package com.example.assignment.network

import com.example.assignment.pojo.ResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    @GET("services/rest/")
   suspend fun getData(
        @Query("method") method: String,
        @Query("api_key") api_key: String,
        @Query("format") format: String,
        @Query("nojsoncallback") nojsoncallback: Int,
        @Query("safe_search") safe_search: Int,
        @Query("tags") tags: String,
        @Query("per_page") per_page: Int,
        @Query("page") page: Int
    ): ResponseModel
}