package com.example.e_commerceapp.ui.newaddress.network

import com.example.e_commerceapp.ui.newaddress.model.PostAddress
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface NewAddressService {

    @POST("customers/{id}/addresses.json")
    suspend fun addNewAddress(@Path("id") userId: Long, @Body address: PostAddress): Response<PostAddress>
}