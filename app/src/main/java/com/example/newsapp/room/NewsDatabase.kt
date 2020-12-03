package com.example.newsapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NewsEntity::class], version = 1,exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract val newsDao: NewsDao

    companion object {
        @Volatile
        private var INSTANCE: NewsDatabase? = null
        fun getInstance(context: Context): NewsDatabase {

            synchronized(NewsDatabase::class) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NewsDatabase::class.java,
                        "news_db"
                    ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
                    INSTANCE = instance
                }

                return instance
            }

        }
    }
}