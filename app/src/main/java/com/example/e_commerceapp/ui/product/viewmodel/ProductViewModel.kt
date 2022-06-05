package com.example.e_commerceapp.ui.product.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.ui.category.model.ProductsResponse
import com.example.e_commerceapp.ui.product.repo.ProductRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(val productRepo: ProductRepo) : ViewModel(){

    private val _vendorsProduct: MutableLiveData<Response<ProductsResponse>> = MutableLiveData()
    val vendorsProduct: LiveData<Response<ProductsResponse>> =_vendorsProduct
    private val _error: MutableLiveData<Throwable> = MutableLiveData()
    val error: LiveData<Throwable> =_error

    private val coroutineExceptionHandler= CoroutineExceptionHandler { _, throwable ->
        _error.postValue(throwable)
       // Log.e(TAG, ": "+throwable.message)
    }
    fun requestVendorsProduct(vendor:String){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            productRepo.getVendorProducts(vendor).onEach{
                _vendorsProduct.postValue(it)
            }.launchIn(viewModelScope)
        }
    }

    fun addFavProduct(){

    }
}