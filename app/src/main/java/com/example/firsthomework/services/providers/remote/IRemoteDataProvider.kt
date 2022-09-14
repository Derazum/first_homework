package com.example.firsthomework.services.providers.remote

import com.example.firsthomework.services.entities.NewsRemote
import com.example.firsthomework.services.utils.Result

interface IRemoteDataProvider {
    suspend fun getData(): Result<List<NewsRemote>, Throwable>
}