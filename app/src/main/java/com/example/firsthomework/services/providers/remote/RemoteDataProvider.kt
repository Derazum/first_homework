package com.example.firsthomework.services.providers.remote

import com.example.firsthomework.services.entities.NewsRemote
import com.example.firsthomework.services.utils.runOperationCatching
import com.example.firsthomework.services.utils.Result
import kotlinx.coroutines.delay
import java.time.LocalDateTime

class RemoteDataProvider : IRemoteDataProvider {
    override suspend fun getData(): Result<List<NewsRemote>, Throwable> {
        return runOperationCatching {
            val list: MutableList<NewsRemote> = mutableListOf();
            for (i in 1..40) {
                delay(100)
                list.add(
                    NewsRemote(text = "Новость ${LocalDateTime.now()}")
                )
            }
            list
        }
    }
}