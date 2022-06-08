package com.example.e_commerceapp.ui.newaddress.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.local.AppSharedPreference
import com.example.e_commerceapp.ui.newaddress.model.PostAddress
import com.example.e_commerceapp.ui.newaddress.repo.NewAddressRepo
import com.example.e_commerceapp.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewAddressVM @Inject constructor(val newAddressRepo: NewAddressRepo, val appSharedPreference: AppSharedPreference) : ViewModel(){

    private val _error: MutableLiveData<Throwable> = MutableLiveData()
    val error: LiveData<Throwable> =_error

    private val coroutineExceptionHandler= CoroutineExceptionHandler { _, throwable ->
        _error.postValue(throwable)
        Log.e("TAG", ": "+throwable.message)
    }

    fun addAddress(addrees: PostAddress){
        var userId = appSharedPreference.getLongValue(Constants.SHARED_ID, 2)
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            newAddressRepo.addNewAddress(userId, addrees)
        }
    }
}