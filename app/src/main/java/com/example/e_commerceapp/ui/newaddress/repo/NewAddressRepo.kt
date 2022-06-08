package com.example.e_commerceapp.ui.newaddress.repo

import com.example.e_commerceapp.ui.newaddress.model.PostAddress
import com.example.e_commerceapp.ui.newaddress.network.NewAddressService


class NewAddressRepo(var newAddressService: NewAddressService): NewAddressRepoI {
    override suspend fun addNewAddress(userId: Long, address: PostAddress) {
        newAddressService.addNewAddress(userId, address)
    }
}