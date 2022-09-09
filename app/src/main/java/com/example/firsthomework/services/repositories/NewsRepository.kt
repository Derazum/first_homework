package com.example.firsthomework.services.repositories

import com.example.firsthomework.services.entities.News
import com.example.firsthomework.services.entities.NewsLocal
import com.example.firsthomework.services.entities.toDomain
import com.example.firsthomework.services.providers.local.ILocalProvider
import com.example.firsthomework.services.providers.remote.IRemoteDataProvider
import com.example.firsthomework.services.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRepository(
    private val localProvider: ILocalProvider,
    private val remoteDataProvider: IRemoteDataProvider
) {

    suspend fun getNews(): Flow<Result<List<News>, Throwable>> {
        return if(localProvider.hasLocalData()) {
            getLocalNews()
        } else {
            getRemoteNews()
        }
    }

    suspend fun getNewNews(): Flow<Result<List<News>, Throwable>> {
        return getRemoteNews()
    }

    private suspend fun getRemoteNews(): Flow<Result<List<News>, Throwable>> {
        return flow {
            try {
                val data = remoteDataProvider.getData().map { it.toDomain() }
                emit(Result.Success(data))
                localProvider.insertData(data = data.map { NewsLocal(0, it.text) })
            } catch (e: Throwable) {
                emit(Result.Error(Throwable(message = e.message)))
            } catch (e: Error) {
                emit(Result.Error(Throwable(message = "Что-то пошло не так")))
            }
        }
    }

    private suspend fun getLocalNews(): Flow<Result<List<News>, Throwable>> {
        return flow {
            try {
                val data = localProvider.getData().map { it.toDomain() }
                emit(Result.Success(data))
            } catch (e: Throwable) {
                emit(Result.Error(Throwable(message = e.message)))
            } catch (e: Error) {
                emit(Result.Error(Throwable(message = "Что-то пошло не так")))
            }
        }
    }

}