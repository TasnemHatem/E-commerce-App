package com.example.e_commerceapp.ui.cart.network

import com.example.e_commerceapp.ui.cart.model.*
import retrofit2.Response
import retrofit2.http.*

interface CartService {
    @POST("draft_orders.json")
    suspend fun createNewCart(@Body createCartBody: CreateCartBody): Response<CreateCartResponse>

    @Headers("Content-Type: application/json")
    @PUT("draft_orders/{id}.json")
    suspend fun updateOrder(
        @Path(value = "id") id: Long,
        @Body createCartBody: CreateCartBody,
    ): Response<CartResponse>

    @GET("draft_orders/{id}.json")
    suspend fun getCart(@Path(value = "id") id: Long): Response<CartResponse>

    @DELETE("draft_orders/{id}.json")
    suspend fun deleteCart(@Path(value = "id", encoded = true) id: Long)

    @GET("draft_orders.json")
    suspend fun getAllCarts(): Response<AllCartsResponse>

    @PUT("draft_orders/{id}/complete.json")
    suspend fun completeCart(@Path(value = "id", encoded = true) id: Long): Response<AllCartsResponse>

    @GET("discount_codes/lookup.json")
    suspend fun applyCoupon(@Query("code") code:String) : Response<CouponResponse>
}