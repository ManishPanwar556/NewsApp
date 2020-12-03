package com.example.newsapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.repository.NewsRepository
import com.example.newsapp.room.NewsDatabase
import com.example.newsapp.room.NewsEntity

class NewsViewModel(application: Application, query: String) : AndroidViewModel(application) {

    private val _properties=MutableLiveData<List<NewsEntity>>()
    val properties:LiveData<List<NewsEntity>>
    get() = _properties
  var newsRepository=NewsRepository(application.applicationContext, query)

    init {
        updateNews()
    }
    private fun getNews():List<NewsEntity>{
        return newsRepository.getNews()
    }
    fun addNews(q: String) {
        newsRepository.deleteNews()
        newsRepository.refreshNews(q)
        updateNews()
    }
    private fun updateNews(){
        _properties.value=getNews()
    }



}