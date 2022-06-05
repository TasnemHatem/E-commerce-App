package com.example.e_commerceapp.ui.cart.repo

import com.example.e_commerceapp.base.network.safeApiCall
import com.example.e_commerceapp.ui.cart.model.CreateCartBody
import com.example.e_commerceapp.ui.cart.model.CreateCartResponse
import com.example.e_commerceapp.ui.cart.network.CartService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class CartRepoImpl(private val cartService: CartService) : CartRepo {


    override suspend fun createNewCart(createCartBody: CreateCartBody,
                                       dispatcher: CoroutineDispatcher
    ): Response<CreateCartResponse> {
        return cartService.createNewCart(createCartBody)

    }



    override fun updateOrder(
        id: Long,
        createCartBody: CreateCartBody,
        dispatcher: CoroutineDispatcher,
    ) = flow {
        emitAll(
            safeApiCall(dispatcher) {
                cartService.updateOrder(id, createCartBody)
            }
        )
    }

    override suspend fun getCart(
        id: Long,
        dispatcher: CoroutineDispatcher,
    ) = flow {
        emitAll(
            safeApiCall(dispatcher) {
                cartService.getCart(id)
            }
        )
    }

    override suspend fun deleteCart(id: Long, dispatcher: CoroutineDispatcher) =
        cartService.deleteCart(id)

    override suspend fun getAllCarts(dispatcher: CoroutineDispatcher) = flow {
        emitAll(
            safeApiCall(dispatcher) {
                cartService.getAllCarts()
            }
        )
    }

    override suspend fun completeCart(
        id: Long,
        dispatcher: CoroutineDispatcher,
    ) = flow {
        emitAll(
            safeApiCall(dispatcher) {
                cartService.completeCart(id)
            }
        )
    }


}