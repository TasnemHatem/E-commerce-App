package com.example.e_commerceapp.ui.category.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.ui.category.model.ProductsResponse
import com.example.e_commerceapp.ui.category.repo.CategoryRepo
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


private const val TAG = "CategoryViewModel"
@HiltViewModel
class CategoryViewModel  @Inject constructor(val categoryRepo: CategoryRepo) : ViewModel(){
    private val _category: MutableLiveData<Response<ProductsResponse>> = MutableLiveData()
    val categoryy: LiveData<Response<ProductsResponse>> =_category

    private val _error: MutableLiveData<Throwable> = MutableLiveData()
    val error: LiveData<Throwable> =_error

    private val coroutineExceptionHandler= CoroutineExceptionHandler { _, throwable ->
        _error.postValue(throwable)
        Log.e(TAG, ": "+throwable.message)
    }

    fun requestCategory(productType:String){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            categoryRepo.getProducts(productType).onEach{
                _category.postValue(it)
            }.launchIn(viewModelScope)
        }
    }
}
