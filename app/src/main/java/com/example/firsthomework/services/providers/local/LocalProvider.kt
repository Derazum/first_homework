package com.example.firsthomework.services.providers.local

import android.content.Context
import com.example.firsthomework.services.database.AppDatabase
import com.example.firsthomework.services.database.NewsDao
import com.example.firsthomework.services.entities.NewsLocal
import com.example.firsthomework.services.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalProvider(
    context: Context,
): ILocalProvider {
    private var newsDao: NewsDao = AppDatabase.getDatabase(context).newsDao()

    override suspend fun getData(): List<NewsLocal> {
        return withContext(Dispatchers.IO) {
            newsDao.getAll()
        }
    }

    override suspend fun insertData(data: List<NewsLocal>) {
        try{
            for (i in 0..data.size) {
                newsDao.insert(data[i])
            }
        } catch (e: Throwable) {
        } catch (e: Error) {
        }
    }

    override fun hasLocalData(): Boolean {
        return try{
            val all = newsDao.getAll()
            all.isNotEmpty()
        } catch (e: Error) {
            false;
        }
    }
}