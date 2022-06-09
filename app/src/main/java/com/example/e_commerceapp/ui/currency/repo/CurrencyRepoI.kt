package com.example.e_commerceapp.ui.currency.repo

import com.example.e_commerceapp.ui.currency.model.CurrencyResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface CurrencyRepoI {
    suspend fun getCurrency(): Flow<Response<CurrencyResponse>>
}