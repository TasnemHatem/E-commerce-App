package com.example.e_commerceapp.ui.cart.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.base.network.DataState
import com.example.e_commerceapp.base.viewmodel.BaseViewModel
import com.example.e_commerceapp.ui.cart.model.CartResponse
import com.example.e_commerceapp.ui.cart.repo.CartRepo
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

    fun requestCart(id: Long) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            cartRepo.getCart(id, Dispatchers.IO).onEach {
                _cart.postValue(it)
            }.launchIn(viewModelScope)
        }
    }
}