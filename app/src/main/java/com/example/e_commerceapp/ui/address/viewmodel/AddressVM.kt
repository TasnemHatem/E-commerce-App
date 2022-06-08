package com.example.e_commerceapp.ui.address.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.local.AppSharedPreference
import com.example.e_commerceapp.ui.address.model.Address
import com.example.e_commerceapp.ui.address.repo.AddressRepoI
import com.example.e_commerceapp.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressVM @Inject constructor(val addressRepo: AddressRepoI, val appSharedPreference: AppSharedPreference) : ViewModel(){

    private val _addresslist : MutableLiveData<List<Address>> = MutableLiveData()
    val addresslist : LiveData<List<Address>> = _addresslist

    private val _error: MutableLiveData<Throwable> = MutableLiveData()
    val error: LiveData<Throwable> =_error

    private val coroutineExceptionHandler= CoroutineExceptionHandler { _, throwable ->
        _error.postValue(throwable)
        Log.e("TAG", ": "+throwable.message)
    }

    fun requestAddresses(){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            var userId = appSharedPreference.getLongValue(Constants.SHARED_ID, 2)
            addressRepo.getAddresses(userId).onEach {
                _addresslist.postValue(it.body()?.addresses)
            }.launchIn(viewModelScope)
        }
    }

    fun deleteAddress(userId: Long, addressId: Long){
        viewModelScope.launch(Dispatchers.IO) {
            addressRepo.deleteAddress(userId, addressId)
        }
        requestAddresses()
    }

    fun changeDefaultAddress(userId: Long, addressId: Long){
        viewModelScope.launch(Dispatchers.IO) {
            addressRepo.setDefaultAddress(userId, addressId)
        }
        requestAddresses()
    }
}