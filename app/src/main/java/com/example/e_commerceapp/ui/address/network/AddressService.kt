package com.example.e_commerceapp.ui.address.network

import com.example.e_commerceapp.ui.address.model.AddressResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface AddressService {
    @GET("customers/{id}/addresses.json")
    suspend fun getAddresses(@Path("id") userId: Long): Response<AddressResponse>

    @DELETE("customers/{userid}/addresses/{addressid}.json")
    suspend fun deleteAddress(@Path("userid") userId: Long, @Path("addressid") addressId: Long)
}