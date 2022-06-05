package com.example.e_commerceapp.ui.order.network

import com.example.e_commerceapp.ui.order.model.OrderResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface OrderService {
    @GET("customers/{id}/orders.json")
    suspend fun getOrders(@Path("id") userId: Long = 6035824083116): Response<OrderResponse>
}