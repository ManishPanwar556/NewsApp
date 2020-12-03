package com.example.newsapp.models


import com.example.newsapp.models.Article
import com.squareup.moshi.Json

data class NewsX(
    @Json(name = "articles")
    val articles: List<Article>,
    @Json(name = "status")
    val status: String,
    @Json(name = "totalResults")
    val totalResults: Int
)