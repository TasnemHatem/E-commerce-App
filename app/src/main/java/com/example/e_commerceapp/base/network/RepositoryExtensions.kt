package com.example.e_commerceapp.base.network

import android.util.Log
import com.example.e_commerceapp.di.NETWORK_TIMEOUT
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withTimeout
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.lang.NullPointerException
import java.net.UnknownHostException

private const val TAG = "RepositoryExtensions"
suspend fun <K, T : Response<K>?> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> Response<K>?,
): Flow<DataState<K>> = flow {
    withTimeout(NETWORK_TIMEOUT) {

        val response = apiCall.invoke()
        if (response != null) {
            val body = response.body()
            if (body != null) {
                emit(DataState.Success(body))
            } else {
                emit(DataState.Error(NullPointerException(),"Null pointer"))//TODO
            }
        } else {
            emit(DataState.Error(NetworkExceptions.UnknownException, "No data"))
        }
        Log.i(TAG, "safeApiCall: $response")
    }
}.onStart {
    emit(DataState.Loading)
}.catch {
    emit(handleError(it))
}.flowOn(dispatcher)

fun <T> handleError(it: Throwable): DataState<T> {
    it.printStackTrace()
    return when (it) {
        is TimeoutCancellationException -> {
            DataState.Error(NetworkExceptions.TimeoutException)
        }

        is UnknownHostException -> {
            DataState.Error(NetworkExceptions.NetworkConnectionException)
        }

//        is Internal -> {
//            DataState.Error(NetworkExceptions.TimeoutException)
//        }

        is IOException -> {
            DataState.Error(NetworkExceptions.UnknownException)
        }

        is HttpException -> {
            DataState.Error(getExceptionType(it))
        }

        else -> {
            it.printStackTrace()
            DataState.Error(NetworkExceptions.UnknownException, it.message)
        }
    }
}

private fun getExceptionType(throwable: HttpException): Exception {
    return when (throwable.code()) {
        401 -> NetworkExceptions.UnAuthorizedException
        404, 500 -> NetworkExceptions.ServerFailException

        403 -> {
            NetworkExceptions.VerifyEmailException
        }
        405 -> {
            // Method Not Allowed
            NetworkExceptions.NotAllowedException
        }
        408 -> {
            NetworkExceptions.TimeoutException
        }
        422 -> {
            NetworkExceptions.WrongDataException
        }
        else -> {
            NetworkExceptions.UnknownException
        }
    }
}