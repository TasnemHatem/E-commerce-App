package com.example.e_commerceapp.ui.currency.repo

import com.example.e_commerceapp.ui.currency.network.CurrencyService
import kotlinx.coroutines.flow.flow

class CurrencyRepo(var currencyService: CurrencyService) : CurrencyRepoI {
    override suspend fun getCurrency() = flow {
        emit(currencyService.getCurrency())
    }
}