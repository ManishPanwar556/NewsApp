package com.example.newsapp.retrofit

import com.example.newsapp.models.NewsX
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsEndPoints {
    @GET("top-headlines")
    fun getHeadLines(
        @Query("q") q:String,
        @Query("apiKey") apiKey: String
    ): Call<NewsX>
}