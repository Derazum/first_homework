package com.example.firsthomework.services.providers.remote

import com.example.firsthomework.services.entities.NewsRemote
import kotlinx.coroutines.delay
import java.time.LocalDateTime

class RemoteDataProvider: IRemoteDataProvider {
    override suspend fun getData(): List<NewsRemote> {
        val list: MutableList<NewsRemote> = mutableListOf();
        for (i in 1..40) {
            delay(100)
            list.add(
                NewsRemote(text = "Новость ${LocalDateTime.now()}")
            )
        }
//        throw Throwable(message = "Ошибка")
        return list
    }
}