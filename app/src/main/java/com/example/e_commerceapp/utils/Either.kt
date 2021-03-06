package com.example.e_commerceapp.utils

sealed class Either<S,E> {

    data class Success<S,E>(val data:S) : Either<S, E>()
    data class Error<S,E>(val errorCode:E,val message:String?=null) : Either<S, E>()
}
