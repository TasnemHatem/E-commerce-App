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
import com.example.e_commerceapp.ui.cart.model.CreateCartBody
import com.example.e_commerceapp.ui.cart.model.CreateCartResponse
import com.example.e_commerceapp.ui.cart.model.DraftOrder
import com.example.e_commerceapp.ui.cart.model.LineItemsItem
import com.example.e_commerceapp.ui.cart.repo.CartRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import retrofit2.Response
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

    private val cartBody =
        CreateCartBody(
            DraftOrder(lineItems = listOf(
                LineItemsItem(title = "lipton", price = "20.00", quantity = 1))
            )
        )

    fun postData(customer: CustomerModel) {
         viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
             val favResponse = async { cartRepo.createNewCart(cartBody,Dispatchers.IO) }
             val cartResponse = async { cartRepo.createNewCart(cartBody,Dispatchers.IO) }
             if (favResponse.await().isSuccessful&&cartResponse.await().isSuccessful) {
                 customer.customer?.favouriteId = favResponse.await().body()?.draftOrder?.id.toString()
                 customer.customer?.cartId = cartResponse.await().body()?.draftOrder?.id.toString()
                 val state = authRepo.register(customer)
                 _signupState.postValue(state)
             }
         }
    }







}