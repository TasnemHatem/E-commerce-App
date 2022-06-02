package com.example.e_commerceapp.base.network

import retrofit2.Response
import java.lang.IllegalArgumentException
import java.net.ConnectException

fun <T> Response<T>.fetchData(): DataState<T> {
    return if (this.isSuccessful) {
        val body = this.body()
        if (body != null) {
            DataState.Success(this.body()!!)
        } else {
            DataState.Error(IllegalArgumentException(), "")
        }
    } else {

        DataState.Error(IllegalArgumentException(), "")
    }
}