package com.example.e_commerceapp.ui.auth.network

import com.example.e_commerceapp.ui.auth.model.Customer
import com.example.e_commerceapp.ui.auth.model.CustomerLoginModel
import com.example.e_commerceapp.ui.auth.model.CustomerModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {
    @POST("customers.json")
    suspend fun register(@Body customer: CustomerModel):
            Response<CustomerModel>

    @GET("customers.json")
    suspend fun login(@Query("email") email: String): Response<CustomerLoginModel>
}

