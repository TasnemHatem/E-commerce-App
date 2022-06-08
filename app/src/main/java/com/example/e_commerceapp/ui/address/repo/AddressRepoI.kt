package com.example.e_commerceapp.ui.address.repo

import com.example.e_commerceapp.ui.address.model.AddressResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Path

interface AddressRepoI {
    suspend fun getAddresses(userId: Long = 6036600684716): Flow<Response<AddressResponse>>
    suspend fun deleteAddress(userId: Long, addressId: Long)
    suspend fun setDefaultAddress(userId: Long, addressId: Long)
}