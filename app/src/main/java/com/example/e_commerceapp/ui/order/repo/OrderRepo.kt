package com.example.e_commerceapp.ui.order.repo

import com.example.e_commerceapp.base.network.safeApiCall
import com.example.e_commerceapp.local.AppSharedPreference
import com.example.e_commerceapp.ui.auth.model.CustomerModel
import com.example.e_commerceapp.ui.cart.model.Customer
import com.example.e_commerceapp.ui.checkout.model.OrderResponse
import com.example.e_commerceapp.ui.checkout.model.PostOrderBody
import com.example.e_commerceapp.ui.order.network.OrderService
import com.example.e_commerceapp.utils.Constants
import com.example.e_commerceapp.utils.Either
import com.example.e_commerceapp.utils.SignUpErrors
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class OrderRepo(
    var orderService: OrderService, private val appSharedPreference: AppSharedPreference,
) : OrderRepoI {
    override suspend fun getOrders(userId: Long) = flow {
        emit(orderService.getOrders(userId))
    }

    override suspend fun postOrder(
        dispatcher: CoroutineDispatcher,
        postOrderBody: PostOrderBody,
    ): Either<OrderResponse, SignUpErrors> {
        val id = appSharedPreference.getLongValue(Constants.SHARED_ID, 896992641196L)

        return try {

            val res = orderService.postOrder(postOrderBody.apply {
                this.order?.customer = Customer(id = id)
            })
            if (res.isSuccessful) {
                val body = res.body()
                if (body != null) {
                    Either.Success(body)
                } else {
                    Either.Error(SignUpErrors.NullValue, res.message())
                }
            } else {
                Either.Error(SignUpErrors.ServerError, res.message())
            }

        } catch (t: Throwable) {
            Either.Error(SignUpErrors.ServerError, t.message)
        }
    }
}