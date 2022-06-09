package com.example.e_commerceapp.ui.currency.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.ui.currency.model.CurrencyResponse
import com.example.e_commerceapp.ui.currency.repo.CurrencyRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyVM @Inject constructor(val currencyRepo: CurrencyRepo) : ViewModel(){
    private val _currencyResponse: MutableLiveData<CurrencyResponse> = MutableLiveData()
    val currencyResponse: LiveData<CurrencyResponse> =_currencyResponse

    fun requestCurrency(){
        viewModelScope.launch(Dispatchers.IO) {
            currencyRepo.getCurrency().onEach {

                _currencyResponse.postValue(it.body())


            }.launchIn(viewModelScope)
        }
    }
}