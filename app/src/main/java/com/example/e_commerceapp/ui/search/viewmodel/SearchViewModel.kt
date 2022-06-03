package com.example.e_commerceapp.ui.search.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.ui.category.model.ProductsResponse
import com.example.e_commerceapp.ui.category.repo.CategoryRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


private const val TAG = "SearchViewModel"
@HiltViewModel
class SearchViewModel @Inject constructor(val searchRepo: CategoryRepo) : ViewModel(){
    private val _searchProd: MutableLiveData<Response<ProductsResponse>> = MutableLiveData()
    val searchProd: LiveData<Response<ProductsResponse>> =_searchProd

    private val _error: MutableLiveData<Throwable> = MutableLiveData()
    val error: LiveData<Throwable> =_error

    private val coroutineExceptionHandler= CoroutineExceptionHandler { _, throwable ->
        _error.postValue(throwable)
        Log.e(TAG, ": "+throwable.message)
    }

    fun requestAllProducts(){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            searchRepo.getAllProducts().onEach{
                _searchProd.postValue(it)
            }.launchIn(viewModelScope)
        }
    }
}
