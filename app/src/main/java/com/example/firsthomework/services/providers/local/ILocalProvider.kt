package com.example.firsthomework.services.providers.local

import com.example.firsthomework.services.entities.NewsLocal

interface ILocalProvider {
    suspend fun getData(): List<NewsLocal>
    suspend fun insertData(data: List<NewsLocal>)
    fun hasLocalData(): Boolean
}