package com.example.e_commerceapp.ui.order.network

import com.example.e_commerceapp.ui.checkout.model.OrderResponse
import com.example.e_commerceapp.ui.checkout.model.PostOrderBody
import com.example.e_commerceapp.ui.order.model.MyOrdersResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface OrderService {
    //anonymous id
    @GET("customers/{id}/orders.json")
    suspend fun getOrders(@Path("id") userId: Long = 6035824083116): Response<MyOrdersResponse>
    //anonymous id
    @POST("orders.json")
    suspend fun postOrder(@Body body: PostOrderBody): Response<OrderResponse>
}