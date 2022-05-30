package com.example.e_commerceapp.ui.home.network

import com.example.e_commerceapp.ui.home.model.VendorsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

interface VendorService {
    @GET("smart_collections.json")
    suspend fun getVendors(): Flow<Response<VendorsResponse>>
}