package com.example.e_commerceapp.ui.newaddress.repo

import com.example.e_commerceapp.ui.newaddress.model.PostAddress

interface NewAddressRepoI {
    suspend fun addNewAddress(userId: Long, address: PostAddress)
}