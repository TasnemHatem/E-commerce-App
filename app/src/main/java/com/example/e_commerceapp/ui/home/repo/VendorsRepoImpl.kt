package com.example.e_commerceapp.ui.home.repo

import com.example.e_commerceapp.ui.home.model.VendorsResponse
import com.example.e_commerceapp.ui.home.network.VendorService
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class VendorsRepoImpl(val vendorService: VendorService) : VendorsRepo {
    override suspend fun getVendors(): Flow<Response<VendorsResponse>> {
        return vendorService.getVendors()
    }
}