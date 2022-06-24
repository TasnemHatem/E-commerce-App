package com.example.e_commerceapp.ui.checkout.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.base.network.DataState
import com.example.e_commerceapp.ui.auth.model.CustomerModel
import com.example.e_commerceapp.ui.cart.model.CreateCartBody
import com.example.e_commerceapp.ui.cart.model.DraftOrder
import com.example.e_commerceapp.ui.cart.repo.CartRepo
import com.example.e_commerceapp.ui.cart.repo.cartBody
import com.example.e_commerceapp.ui.checkout.model.OrderResponse
import com.example.e_commerceapp.ui.checkout.model.PostOrderBody
import com.example.e_commerceapp.ui.order.repo.OrderRepo
import com.example.e_commerceapp.utils.Either
import com.example.e_commerceapp.utils.SignUpErrors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "CheckoutViewModel"

@HiltViewModel
class CheckoutViewModel @Inject constructor(val orderRepo: OrderRepo, val cartRepo: CartRepo) :
    ViewModel() {
    private val _orderResponse: MutableLiveData<Either<OrderResponse, SignUpErrors>?> =
        MutableLiveData()
    val orderResponse: LiveData<Either<OrderResponse, SignUpErrors>?> = _orderResponse


    fun postOrder(postOrderBody: PostOrderBody) {
        viewModelScope.launch(Dispatchers.IO) {
            val state = orderRepo.postOrder(Dispatchers.IO, postOrderBody)
            cartRepo.updateOrder(CreateCartBody(DraftOrder(lineItems = mutableListOf())),
                Dispatchers.IO).onEach {
                when (it) {
                    is DataState.Success -> {
                        _orderResponse.postValue(state)
                    }
                    is DataState.Error -> _orderResponse.postValue(state)
                }
            }.launchIn(viewModelScope)

        }
    }

}