package com.example.e_commerceapp.base.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler

private const val TAG = "BaseViewModel"
open class BaseViewModel : ViewModel() {
    private val _error: MutableLiveData<Throwable> = MutableLiveData()
    val error: LiveData<Throwable> =_error

    val coroutineExceptionHandler= CoroutineExceptionHandler { _, throwable ->
        _error.postValue(throwable)
        Log.e(TAG, ": ${this.javaClass.name} "+throwable.message)
    }

}