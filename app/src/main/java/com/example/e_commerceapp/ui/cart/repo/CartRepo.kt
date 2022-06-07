package com.example.e_commerceapp.ui.cart.repo

import com.example.e_commerceapp.base.network.DataState
import com.example.e_commerceapp.ui.cart.model.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import retrofit2.Response


interface CartRepo {
    suspend fun createNewCart(
        createCartBody: CreateCartBody,
        dispatcher: CoroutineDispatcher,
    ): Response<CreateCartResponse>

    fun updateOrder(
        createCartBody: CreateCartBody,
        dispatcher: CoroutineDispatcher,
    ): Flow<DataState<CartResponse>>

    suspend fun getCart(
        dispatcher: CoroutineDispatcher,
    ): Flow<DataState<CartResponse>>

    suspend fun deleteCart(
        dispatcher: CoroutineDispatcher,
    )

    suspend fun getAllCarts(
        dispatcher: CoroutineDispatcher,
    ): Flow<DataState<AllCartsResponse>>

    suspend fun completeCart(
        dispatcher: CoroutineDispatcher,
    ): Flow<DataState<AllCartsResponse>>

    suspend fun applyCoupon(
        coupon: String,
        dispatcher: CoroutineDispatcher,
    ): Flow<DataState<CouponResponse>>
}