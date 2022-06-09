package com.example.e_commerceapp.ui.currency.network

import com.example.e_commerceapp.ui.currency.model.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET

interface CurrencyService {
    @GET("v6/53a3833db04a38a32778d89f/latest/USD")
    suspend fun getCurrency(): Response<CurrencyResponse>
}