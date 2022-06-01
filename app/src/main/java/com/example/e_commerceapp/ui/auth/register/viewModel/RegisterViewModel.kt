package com.example.e_commerceapp.ui.auth.register.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.ui.auth.model.Customer
import com.example.e_commerceapp.ui.auth.model.CustomerModel
import com.example.e_commerceapp.ui.auth.model.Either
import com.example.e_commerceapp.ui.auth.model.SignUpErrors
import com.example.e_commerceapp.ui.auth.repo.AuthRepo
import com.example.e_commerceapp.ui.home.model.VendorsResponse
import com.example.e_commerceapp.ui.home.repo.VendorsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


private const val TAG = "AuthViewModel"
@HiltViewModel
class RegisterViewModel  @Inject constructor(val authRepo: AuthRepo) : ViewModel(){

    private val _signupSuccess: MutableLiveData<Either<CustomerModel, SignUpErrors>?> = MutableLiveData()
    val signupSuccess: LiveData<Either<CustomerModel, SignUpErrors>?> =_signupSuccess
    private val _error:MutableLiveData<Throwable> = MutableLiveData()
    val error: LiveData<Throwable> =_error

    private val coroutineExceptionHandler= CoroutineExceptionHandler { _, throwable ->
        _error.postValue(throwable)
        Log.e("TAG", ": "+throwable.message)
    }
     fun postData(customer: CustomerModel) {
         viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val state = authRepo.register(customer)
             _signupSuccess.postValue(state)
         }
     }





}