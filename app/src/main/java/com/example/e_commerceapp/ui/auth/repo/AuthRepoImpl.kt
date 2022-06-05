package com.example.e_commerceapp.ui.auth.repo

import com.example.e_commerceapp.local.AppSharedPreference
import com.example.e_commerceapp.ui.auth.model.*
import com.example.e_commerceapp.ui.auth.network.AuthService
import com.example.e_commerceapp.utils.Constants.SHARED_CART_ID
import com.example.e_commerceapp.utils.Constants.SHARED_FAV_ID
import com.example.e_commerceapp.utils.Constants.SHARED_ID
import com.example.e_commerceapp.utils.Constants.SHARED_MAIL
import com.example.e_commerceapp.utils.Either
import com.example.e_commerceapp.utils.LoginErrors
import com.example.e_commerceapp.utils.SignUpErrors


class AuthRepoImpl(
    private val authService: AuthService,
    private val appSharedPreference: AppSharedPreference
) : AuthRepo {

    override suspend fun register(customer: CustomerModel): Either<CustomerModel, SignUpErrors> {
        return try {
            val res = authService.register(customer)
            if (res.isSuccessful) {
                val body = res.body()
                if (body != null) {
                    cacheData(body.customer!!)
                    Either.Success(body)
                } else {
                    Either.Error(SignUpErrors.NullValue, res.message())
                }
            } else {
                if (res.code() == 422) {
                    Either.Error(SignUpErrors.EmailAlreadyExist, res.message())
                } else {
                    Either.Error(SignUpErrors.ServerError, res.message())
                }
            }

        } catch (t: Throwable) {
            Either.Error(SignUpErrors.ServerError, t.message)
        }
    }


    private fun cacheData(customer: Customer) {
        appSharedPreference.setValue(SHARED_ID, customer.customerId)
        appSharedPreference.setValue(SHARED_MAIL, customer.email)
        appSharedPreference.setValue(SHARED_CART_ID, customer.cartId)
        appSharedPreference.setValue(SHARED_FAV_ID, customer.favouriteId)
    }

    override suspend fun login(
        email: String,
        password: String
    ): Either<CustomerLoginModel, LoginErrors> {
        return try {
            val res = authService.login(email)
            if (res.isSuccessful) {
                val customer = res.body()?.customer?.first() {
                    cacheData(it!!)
                    it?.email.equals(email)
                } ?: return Either.Error(LoginErrors.CustomerNotFound, "CustomerNotFound")
                return if (customer.password.equals(password)) {
                    if (res.body() != null) {
                        Either.Success(res.body()!!)
                    }
                    else {
                        Either.Error(LoginErrors.NullValue, res.message())
                    }
                } else Either.Error(
                    LoginErrors.IncorrectEmailOrPassword,
                    "Please enter correct email or password"
                )
            } else
                return Either.Error(LoginErrors.ServerError, res.message())

        } catch (t: Throwable) {
            Either.Error(LoginErrors.ServerError, t.message)
        }
    }
}

