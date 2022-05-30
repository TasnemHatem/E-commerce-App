package com.example.e_commerceapp.ui.home.network

import com.example.e_commerceapp.ui.home.model.VendorsResponse
import retrofit2.Response
import retrofit2.http.GET

interface VendorService {
    @GET("smart_collections.json")
    suspend fun getVendors(): Response<VendorsResponse>
}