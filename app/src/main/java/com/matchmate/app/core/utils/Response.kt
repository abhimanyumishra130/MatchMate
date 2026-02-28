package com.matchmate.app.core.utils

sealed class Response<out T> {
    data object Loading: Response<Nothing>()
    data class Success<T>(val data:T): Response<T>()
    data class Error(val message: String, val errorCode: String): Response<Nothing>()
}