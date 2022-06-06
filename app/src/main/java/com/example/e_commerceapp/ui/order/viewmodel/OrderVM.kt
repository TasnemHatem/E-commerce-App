package com.example.e_commerceapp.ui.order.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.ui.order.model.Order
import com.example.e_commerceapp.ui.order.repo.OrderRepo
import com.example.e_commerceapp.ui.order.repo.OrderRepoI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderVM @Inject constructor(val orderRepo: OrderRepo) : ViewModel(){
    private val _orderlist: MutableLiveData<List<Order>> = MutableLiveData()
    val orderlist: LiveData<List<Order>> =_orderlist

    private val _error: MutableLiveData<Throwable> = MutableLiveData()
    val error: LiveData<Throwable> =_error

    private val coroutineExceptionHandler= CoroutineExceptionHandler { _, throwable ->
        _error.postValue(throwable)
        Log.e("TAG", ": "+throwable.message)
    }

    fun requestOrders(){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            orderRepo.getOrders().onEach {

                _orderlist.postValue(it.body()?.orders)

            }.launchIn(viewModelScope)
        }
    }

}