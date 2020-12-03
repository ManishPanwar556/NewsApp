package com.example.newsapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="news_table")
data class NewsEntity(
    @PrimaryKey
    val imageUrl:String,
    val title:String,
    val content:String?,
    val description:String,
    val newsUrl:String,
    val author:String?,
    val query:String,

)