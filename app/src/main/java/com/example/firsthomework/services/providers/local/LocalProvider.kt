package com.example.firsthomework.services.providers.local

import android.content.Context
import com.example.firsthomework.services.database.AppDatabase
import com.example.firsthomework.services.database.NewsDao
import com.example.firsthomework.services.entities.NewsLocal
import com.example.firsthomework.services.utils.Result
import com.example.firsthomework.services.utils.runOperationCatching
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalProvider(
    context: Context,
) : ILocalProvider {
    private var newsDao: NewsDao = AppDatabase.getDatabase(context).newsDao()

    override suspend fun getData(): Result<List<NewsLocal>, Throwable> {
        return runOperationCatching {
            withContext(Dispatchers.IO) {
                newsDao.getAll()
            }
        }
    }

    override suspend fun insertData(data: List<NewsLocal>): Result<Unit, Throwable> {
        return runOperationCatching {
            for (i in data.indices) {
                newsDao.insert(data[i])
            }
        }
    }

    override fun hasLocalData(): Boolean {
        return try {
            val all = newsDao.getAll()
            all.isNotEmpty()
        } catch (e: Error) {
            false;
        }
    }
}