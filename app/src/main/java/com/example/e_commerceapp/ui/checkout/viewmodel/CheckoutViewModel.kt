package com.example.e_commerceapp.ui.checkout.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.base.network.DataState
import com.example.e_commerceapp.ui.checkout.model.OrderResponse
import com.example.e_commerceapp.ui.checkout.model.PostOrderBody
import com.example.e_commerceapp.ui.order.repo.OrderRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel  @Inject constructor(val orderRepo: OrderRepo): ViewModel() {
    private val _orderResponse: MutableLiveData<DataState<OrderResponse>> = MutableLiveData()
    val orderResponse: LiveData<DataState<OrderResponse>> = _orderResponse


    fun postOrder(postOrderBody: PostOrderBody){
        viewModelScope.launch(Dispatchers.IO) {
            orderRepo.postOrder(Dispatchers.IO,postOrderBody).onEach {
                _orderResponse.postValue(it)
            }.launchIn(viewModelScope)
        }
    }

}