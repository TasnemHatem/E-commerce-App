package com.example.e_commerceapp.ui.home.repo

import com.example.e_commerceapp.ui.home.network.VendorService
import kotlinx.coroutines.flow.flow

class VendorsRepoImpl(val vendorService: VendorService) : VendorsRepo {
    override suspend fun getVendors() = flow {
        emit(vendorService.getVendors())
    }
}