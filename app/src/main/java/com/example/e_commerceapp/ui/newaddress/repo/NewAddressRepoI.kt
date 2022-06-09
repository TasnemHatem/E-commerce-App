package com.example.e_commerceapp.ui.newaddress.repo

import com.example.e_commerceapp.ui.newaddress.model.PostAddress
import retrofit2.Response

interface NewAddressRepoI {
    suspend fun addNewAddress(userId: Long, address: PostAddress): Response<PostAddress>
}