package com.example.firsthomework.services.database

import androidx.room.*
import com.example.firsthomework.services.entities.NewsLocal

@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    fun getAll(): List<NewsLocal>

    @Insert
    fun insert(news: NewsLocal)
}