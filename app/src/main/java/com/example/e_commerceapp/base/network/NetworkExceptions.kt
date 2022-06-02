package com.example.e_commerceapp.base.network

sealed class NetworkExceptions: Exception(){
    object NetworkConnectionException : NetworkExceptions()
    object UnknownException : NetworkExceptions()
    object UnAuthorizedException : NetworkExceptions()
    object ServerFailException : NetworkExceptions()
    object TimeoutException : NetworkExceptions()
    object VerifyEmailException : NetworkExceptions()
    object NotAllowedException : NetworkExceptions()
    object WrongDataException : NetworkExceptions()
    data class CustomException(val msg: String) : NetworkExceptions()
}
