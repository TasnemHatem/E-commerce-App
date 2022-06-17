package com.example.e_commerceapp.ui.home.repo

import android.util.Log
import com.example.e_commerceapp.ui.home.network.VendorService
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class VendorsRepoImpl(private val vendorService: VendorService) : VendorsRepo {
    override suspend fun getVendors() = flow {
        emit(vendorService.getVendors())
    }.catch {
        Log.e("vendoreRepo",it.toString())
    }
}