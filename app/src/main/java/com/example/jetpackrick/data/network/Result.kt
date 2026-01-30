package com.example.jetpackrick.data.network

import com.example.jetpackrick.data.CharacterResponse

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()
}