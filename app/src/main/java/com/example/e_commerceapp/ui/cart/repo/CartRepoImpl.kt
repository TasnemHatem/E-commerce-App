package com.example.e_commerceapp.ui.cart.repo

import com.example.e_commerceapp.base.network.DataState
import com.example.e_commerceapp.base.network.safeApiCall
import com.example.e_commerceapp.local.AppSharedPreference
import com.example.e_commerceapp.ui.cart.model.*
import com.example.e_commerceapp.ui.cart.network.CartService
import com.example.e_commerceapp.utils.Constants
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class CartRepoImpl(
    private val cartService: CartService,
    private val appSharedPreference: AppSharedPreference,
) : CartRepo {


    override suspend fun createNewCart(
        createCartBody: CreateCartBody,
        dispatcher: CoroutineDispatcher,
    ): Response<CreateCartResponse> {
        return cartService.createNewCart(createCartBody)
    }

    private val cartBody = LineItemsItem(title = "lipton", price = "20.00", quantity = 1)


    override fun updateOrder(
        createCartBody: CreateCartBody,
        dispatcher: CoroutineDispatcher,
    ) = flow {
        val id = appSharedPreference.getLongValue(Constants.SHARED_CART_ID, 896992641196L)
        createCartBody.draftOrder?.lineItems?.remove(cartBody)
        createCartBody.draftOrder?.lineItems?.add(cartBody)
        emitAll(
            safeApiCall(dispatcher) {
                cartService.updateOrder(id, createCartBody)
            }
        )
    }

    override suspend fun getCart(
        dispatcher: CoroutineDispatcher,
    ) = flow {
        val id = appSharedPreference.getLongValue(Constants.SHARED_CART_ID, 896992641196L)
        emitAll(
            safeApiCall(dispatcher) {
                cartService.getCart(id)
            }
        )
    }

    override suspend fun deleteCart(dispatcher: CoroutineDispatcher) {
        val id = appSharedPreference.getLongValue(Constants.SHARED_CART_ID, 896992641196L)
        cartService.deleteCart(id)
    }

    override suspend fun getAllCarts(dispatcher: CoroutineDispatcher) = flow {
        emitAll(
            safeApiCall(dispatcher) {
                cartService.getAllCarts()
            }
        )
    }

    override suspend fun completeCart(
        dispatcher: CoroutineDispatcher,
    ) = flow {
        val id = appSharedPreference.getLongValue(Constants.SHARED_CART_ID, 896992641196L)
        emitAll(
            safeApiCall(dispatcher) {
                cartService.completeCart(id)
            }
        )
    }

    override suspend fun applyCoupon(
        coupon: String,
        dispatcher: CoroutineDispatcher,
    ) = flow {
        emitAll(
            safeApiCall(dispatcher) {
                cartService.applyCoupon(coupon)
            }
        )
    }


}