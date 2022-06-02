package com.example.e_commerceapp.ui.auth.login.viewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.R
import com.example.e_commerceapp.ui.auth.model.*
import com.example.e_commerceapp.ui.auth.repo.AuthRepo
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


private const val TAG = "AuthViewModel"
@HiltViewModel
class LoginViewModel @Inject constructor(val authRepo: AuthRepo) : ViewModel(){

    private val _loginState: MutableLiveData<Either<CustomerLoginModel, LoginErrors>?> = MutableLiveData()
    val loginState: LiveData<Either<CustomerLoginModel, LoginErrors>?> =_loginState
    private val _error:MutableLiveData<Throwable> = MutableLiveData()
    val error: LiveData<Throwable> =_error

    private val coroutineExceptionHandler= CoroutineExceptionHandler { _, throwable ->
        _error.postValue(throwable)
        Log.e("TAG", ": "+throwable.message)
    }

    fun getData(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val state = authRepo.login(email,password)
            _loginState.postValue(state)
        }
    }


}