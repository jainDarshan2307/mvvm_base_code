package com.example.mvvmbasecode.network

sealed class APIState<T> {
    class Idle<T> : APIState<T>()
    class Loading<T> : APIState<T>()
    data class Success<T>(val response: T) : APIState<T>()
    data class Error<T>(val error: String) : APIState<T>()
}