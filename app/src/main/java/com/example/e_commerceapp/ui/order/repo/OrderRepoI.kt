package com.example.e_commerceapp.ui.order.repo

import com.example.e_commerceapp.ui.order.model.OrderResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface OrderRepoI {
    suspend fun getOrders(userId: Long = 6035824083116): Flow<Response<OrderResponse>>
}