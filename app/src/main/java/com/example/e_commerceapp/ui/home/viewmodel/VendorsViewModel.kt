package com.example.e_commerceapp.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.ui.home.model.VendorsResponse
import com.example.e_commerceapp.ui.home.repo.VendorsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class VendorsViewModel @Inject constructor(val vendorsRepo: VendorsRepo) : ViewModel(){

    private val _vendors:MutableLiveData<Response<VendorsResponse>> = MutableLiveData()
    val vendors:LiveData<Response<VendorsResponse>> =_vendors
    private val _error:MutableLiveData<Throwable> = MutableLiveData()
    val error:LiveData<Throwable> =_error

    private val coroutineExceptionHandler= CoroutineExceptionHandler { _, throwable ->
        _error.postValue(throwable)
    }
    fun requestVendors(){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            vendorsRepo.getVendors().collect{
                _vendors.postValue(it)
            }
        }
    }
}