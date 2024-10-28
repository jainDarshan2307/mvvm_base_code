package com.example.mvvmbasecode.repository

import com.example.mvvmbasecode.network.APIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository {
    suspend fun <T> safeAPICall(
        apiCall: suspend () -> T
    ): APIState<T> {
        return withContext(Dispatchers.IO) {
            try {
                APIState.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        APIState.Error(throwable.response()?.message() ?: "")
                    }

                    else -> {
                        APIState.Error(throwable.message ?: "")
                    }
                }

            }
        }
    }
}