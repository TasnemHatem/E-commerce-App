package com.example.e_commerceapp.base.network

sealed class DataState<out R> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(var exception: Exception, var msg: String? = null) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
    object Idle : DataState<Nothing>()
}