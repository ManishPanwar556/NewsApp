package com.example.newsapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNewsData(newsEntity: NewsEntity)
    @Query("DELETE from news_table")
    suspend fun deleteNews()
    @Query("SELECT * FROM news_table")
    fun getNews():List<NewsEntity>
}