package com.example.e_commerceapp.ui.order.repo

import com.example.e_commerceapp.base.network.safeApiCall
import com.example.e_commerceapp.ui.checkout.model.PostOrderBody
import com.example.e_commerceapp.ui.order.network.OrderService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class OrderRepo(var orderService: OrderService) : OrderRepoI {
    override suspend fun getOrders(userId: Long) = flow {
        emit(orderService.getOrders(userId))
    }

    override suspend fun postOrder(dispatcher: CoroutineDispatcher, postOrderBody: PostOrderBody) =
        flow {
            emitAll(
                safeApiCall(dispatcher) {
                    orderService.postOrder(postOrderBody)
                }
            )
        }
}