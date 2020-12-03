package com.example.newsapp.models


import com.squareup.moshi.Json

data class Source(
    @Json(name = "id")
    val id: Any,
    @Json(name = "name")
    val name: String
)