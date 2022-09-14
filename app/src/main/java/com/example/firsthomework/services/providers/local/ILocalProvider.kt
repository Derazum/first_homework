package com.example.firsthomework.services.providers.local

import com.example.firsthomework.services.entities.NewsLocal
import com.example.firsthomework.services.utils.Result

interface ILocalProvider {
    suspend fun getData(): Result<List<NewsLocal>, Throwable>
    suspend fun insertData(data: List<NewsLocal>): Result<Unit, Throwable>
    fun hasLocalData(): Boolean
}