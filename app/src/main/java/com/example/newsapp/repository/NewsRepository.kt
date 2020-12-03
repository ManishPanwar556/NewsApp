package com.example.newsapp.repository


import android.content.Context
import android.util.Log
import com.example.newsapp.models.NewsX
import com.example.newsapp.retrofit.NewsApiService
import com.example.newsapp.retrofit.NewsEndPoints
import com.example.newsapp.room.NewsDao
import com.example.newsapp.room.NewsDatabase
import com.example.newsapp.room.NewsEntity
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback

class NewsRepository(context: Context, query:String) {
    private var data:NewsDao=NewsDatabase.getInstance(context).newsDao
    init {
        refreshNews(query)
    }
    fun refreshNews(query: String){
        val request = NewsApiService.buildService(NewsEndPoints::class.java)
        val call = request.getHeadLines(query, "1d5428b694dc4529874d44afe8d9e626")
        call.enqueue(object : Callback<NewsX> {
            override fun onResponse(call: Call<NewsX>, response: retrofit2.Response<NewsX>) {
                if (response.isSuccessful) {
                    val list = response.body()?.articles
                    list?.forEach { article->
                        val newsEntity=NewsEntity(article.urlToImage,article.title,article.content,article.description,article.url,article.author,query)
                        GlobalScope.launch(Dispatchers.IO) {
                            data.insertNewsData(newsEntity)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<NewsX>, t: Throwable) {
                Log.e("Error","$t")
            }
        })
    }
    fun deleteNews() {
        GlobalScope.launch(Dispatchers.IO) {
            data.deleteNews()
        }

    }
    fun getNews():List<NewsEntity>{
         return data.getNews()
    }
}