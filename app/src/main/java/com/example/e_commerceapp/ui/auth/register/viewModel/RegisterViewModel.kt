package com.example.e_commerceapp.ui.auth.register.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.ui.auth.model.CustomerModel
import com.example.e_commerceapp.utils.Either
import com.example.e_commerceapp.utils.SignUpErrors
import com.example.e_commerceapp.ui.auth.repo.AuthRepo
import com.example.e_commerceapp.ui.cart.repo.CartRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject


private const val TAG = "AuthViewModel"
@HiltViewModel
class RegisterViewModel  @Inject constructor(val authRepo: AuthRepo,val cartRepo: CartRepo) : ViewModel(){

    private val _signupState: MutableLiveData<Either<CustomerModel, SignUpErrors>?> = MutableLiveData()
    val signupState: LiveData<Either<CustomerModel, SignUpErrors>?> =_signupState
    private val _error:MutableLiveData<Throwable> = MutableLiveData()
    val error: LiveData<Throwable> =_error

    private val coroutineExceptionHandler= CoroutineExceptionHandler { _, throwable ->
        _error.postValue(throwable)
        Log.e("TAG", ": "+throwable.message)
    }

    fun postData(customer: CustomerModel) {
         viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            //val favResponse = async { cartRepo.createNewCart() }
            val state = authRepo.register(customer)
             _signupState.postValue(state)
         }
    }







}