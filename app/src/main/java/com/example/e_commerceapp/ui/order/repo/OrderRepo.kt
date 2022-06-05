package com.example.e_commerceapp.ui.order.repo

import com.example.e_commerceapp.ui.order.network.OrderService
import kotlinx.coroutines.flow.flow

class OrderRepo(var orderService: OrderService): OrderRepoI{
    override suspend fun getOrders(userId: Long) = flow {
        emit(orderService.getOrders(userId))
    }
}