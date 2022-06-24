package com.example.e_commerceapp.ui.newaddress.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.local.AppSharedPreference
import com.example.e_commerceapp.ui.newaddress.model.ErrorPojo
import com.example.e_commerceapp.ui.newaddress.model.PostAddress
import com.example.e_commerceapp.ui.newaddress.repo.NewAddressRepo
import com.example.e_commerceapp.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.google.gson.Gson


@HiltViewModel
class NewAddressVM @Inject constructor(val newAddressRepo: NewAddressRepo, val appSharedPreference: AppSharedPreference) : ViewModel(){

    private val _error: MutableLiveData<Throwable> = MutableLiveData()
    val error: LiveData<Throwable> =_error
    private val _addAddressError: MutableLiveData<String> = MutableLiveData()
    val addAddressError: LiveData<String> = _addAddressError
    val addAddressSuccess: MutableLiveData<String> = MutableLiveData()

    private val coroutineExceptionHandler= CoroutineExceptionHandler { _, throwable ->
        _error.postValue(throwable)
        Log.i("EMYTAG", "Ya Errorrrrrrrrrrrrrr: "+ throwable.message)
    }

    fun addAddress(addrees: PostAddress){
        var userId = appSharedPreference.getLongValue(Constants.SHARED_ID, 2)
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val state = newAddressRepo.addNewAddress(userId, addrees)
            if(state.isSuccessful){
                addAddressSuccess.postValue("Success")
            }else{
                val jsonError = state.errorBody()?.string() as String
                val gson = Gson()
                val errorObj = gson.fromJson(jsonError,ErrorPojo::class.java)
                var countryMsg = ""
                var signatureMsg = ""
                if(!errorObj.errors.country.isNullOrEmpty()){
                    countryMsg = errorObj.errors.country[0]
                    _addAddressError.postValue(countryMsg)
                }
                if(!errorObj.errors.signature.isNullOrEmpty()){
                    signatureMsg = "The address " + errorObj.errors.signature[0]
                    _addAddressError.postValue(signatureMsg)
                }
            }

        }
    }
}