package com.example.e_commerceapp.ui.order.repo

import com.example.e_commerceapp.base.network.DataState
import com.example.e_commerceapp.ui.checkout.model.OrderResponse
import com.example.e_commerceapp.ui.checkout.model.PostOrderBody
import com.example.e_commerceapp.ui.order.model.MyOrdersResponse
import com.example.e_commerceapp.utils.Either
import com.example.e_commerceapp.utils.SignUpErrors
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface OrderRepoI {
    suspend fun getOrders(userId: Long = 6035824083116): Flow<Response<MyOrdersResponse>>
    suspend fun postOrder(
        dispatcher: CoroutineDispatcher,
        postOrderBody: PostOrderBody,
    ): Either<OrderResponse, SignUpErrors>
}