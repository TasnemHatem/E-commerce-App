package com.example.e_commerceapp.ui.cart.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.base.network.DataState
import com.example.e_commerceapp.base.viewmodel.BaseViewModel
import com.example.e_commerceapp.ui.cart.model.*
import com.example.e_commerceapp.ui.cart.repo.CartRepo
import com.example.e_commerceapp.ui.cart.repo.cartBody
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "CartViewModel"

@HiltViewModel
class CartViewModel @Inject constructor(val cartRepo: CartRepo) : BaseViewModel() {
    private val _cart: MutableLiveData<DataState<CartResponse>> = MutableLiveData()
    val cart: LiveData<DataState<CartResponse>> = _cart
    private val _coupon: MutableLiveData<DataState<CouponResponse>> = MutableLiveData()
    val coupon: LiveData<DataState<CouponResponse>> = _coupon
    private val _addItemResponse: MutableLiveData<DataState<Any>> = MutableLiveData()
    val addItemResponse: LiveData<DataState<Any>> = _addItemResponse

    fun requestCart() {
        viewModelScope.launch(Dispatchers.IO) {
            cartRepo.getCart(Dispatchers.IO).onEach {
                _cart.postValue(it)
            }.launchIn(viewModelScope)
        }
    }

    fun updateCart(createCartBody: CreateCartBody) {
        viewModelScope.launch(Dispatchers.IO) {
            cartRepo.updateOrder(createCartBody, Dispatchers.IO).onEach {
                _cart.postValue(it)
            }.launchIn(viewModelScope)
        }
    }

    fun addItem(item: LineItemsItem) {
        viewModelScope.launch(Dispatchers.IO) {
            cartRepo.getCart(Dispatchers.IO).onEach { dataState ->
                when (dataState) {
                    is DataState.Success -> {
                        dataState.data.draftOrder?.lineItems?.find { it?.variantId == item.variantId }
                        dataState.data.draftOrder?.lineItems?.remove(cartBody)
                        dataState.data.draftOrder?.lineItems?.add(item)
                        cartRepo.updateOrder(
                            CreateCartBody(
                                DraftOrder(
                                    lineItems = dataState.data.draftOrder?.lineItems
                                )
                            ),
                            Dispatchers.IO).onEach {
                            _addItemResponse.postValue(it)
                        }.launchIn(viewModelScope)
                    }
                    else -> {
                        _addItemResponse.postValue(dataState)
                    }
                }
            }.launchIn(viewModelScope)

        }
    }

    fun applyCoupon(coupon: String) {
        viewModelScope.launch(Dispatchers.IO) {
            cartRepo.applyCoupon(coupon, Dispatchers.IO).onEach {
                _coupon.postValue(it)
            }.launchIn(viewModelScope)
        }
    }
}