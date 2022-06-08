package com.example.e_commerceapp.ui.address.repo

import com.example.e_commerceapp.ui.address.model.AddressResponse
import com.example.e_commerceapp.ui.address.network.AddressService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class AddressRepo (var addressService: AddressService) : AddressRepoI {
    override suspend fun getAddresses(userId: Long)= flow {
        emit(addressService.getAddresses(userId))
    }

    override suspend fun deleteAddress(userId: Long, addressId: Long) {
        addressService.deleteAddress(userId, addressId)
    }
}