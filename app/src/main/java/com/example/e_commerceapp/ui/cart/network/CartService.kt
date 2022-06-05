package com.example.e_commerceapp.ui.cart.network

import com.example.e_commerceapp.ui.cart.model.AllCartsResponse
import com.example.e_commerceapp.ui.cart.model.CartResponse
import com.example.e_commerceapp.ui.cart.model.CreateCartBody
import com.example.e_commerceapp.ui.cart.model.CreateCartResponse
import retrofit2.Response
import retrofit2.http.*

interface CartService {
    @POST("draft_orders.json")
    suspend fun createNewCart(@Body createCartBody: CreateCartBody): Response<CreateCartResponse>

    @PUT("draft_orders/{id}.json")
    fun updateOrder(
        @Path(value = "id", encoded = true) id: Long,
        @Body createCartBody: CreateCartBody,
    ): Response<CreateCartResponse>

    @GET("draft_orders/{id}.json")
    suspend fun getCart(@Path(value = "id") id: Long): Response<CartResponse>

    @DELETE("draft_orders/{id}.json")
    fun deleteCart(@Path(value = "id", encoded = true) id: Long)

    @GET("draft_orders.json")
    suspend fun getAllCarts(): Response<AllCartsResponse>

    @PUT("draft_orders/{id}/complete.json")
    fun completeCart(@Path(value = "id", encoded = true) id: Long): Response<AllCartsResponse>
}