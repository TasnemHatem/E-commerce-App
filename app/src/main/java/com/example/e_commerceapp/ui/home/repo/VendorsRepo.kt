package com.example.e_commerceapp.ui.home.repo

import com.example.e_commerceapp.ui.home.model.VendorsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface VendorsRepo  {
    suspend fun getVendors(): Flow<Response<VendorsResponse>>
}