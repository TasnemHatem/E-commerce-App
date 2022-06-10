package com.example.e_commerceapp.ui.checkout.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.base.network.DataState
import com.example.e_commerceapp.ui.auth.model.CustomerModel
import com.example.e_commerceapp.ui.checkout.model.OrderResponse
import com.example.e_commerceapp.ui.checkout.model.PostOrderBody
import com.example.e_commerceapp.ui.order.repo.OrderRepo
import com.example.e_commerceapp.utils.Either
import com.example.e_commerceapp.utils.SignUpErrors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel  @Inject constructor(val orderRepo: OrderRepo): ViewModel() {
    private val _orderResponse: MutableLiveData<Either<OrderResponse, SignUpErrors>?> = MutableLiveData()
    val orderResponse: LiveData<Either<OrderResponse, SignUpErrors>?> = _orderResponse


    fun postOrder(postOrderBody: PostOrderBody){
        viewModelScope.launch(Dispatchers.IO) {
            val state =orderRepo.postOrder(Dispatchers.IO,postOrderBody)
            _orderResponse.postValue(state)
        }
    }

}