package com.example.firsthomework.services.providers.remote

import com.example.firsthomework.services.entities.NewsRemote

interface IRemoteDataProvider {
    suspend fun getData(): List<NewsRemote>
}