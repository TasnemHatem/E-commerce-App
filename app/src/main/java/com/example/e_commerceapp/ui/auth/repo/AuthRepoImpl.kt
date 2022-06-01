package com.example.e_commerceapp.ui.auth.repo

import com.example.e_commerceapp.local.AppSharedPreference
import com.example.e_commerceapp.ui.auth.model.*
import com.example.e_commerceapp.ui.auth.network.AuthService
import com.example.e_commerceapp.ui.home.network.VendorService
import com.example.e_commerceapp.ui.home.repo.VendorsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.lang.NullPointerException
import javax.inject.Inject

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
        appSharedPreference.setValue("shared_id", customer.customerId)
        appSharedPreference.setValue("shared_email", customer.email)
        appSharedPreference.setValue("shared_cart_id", customer.cartId)
        appSharedPreference.setValue("shared_favourite_id", customer.customerId)
    }

    override suspend fun login(
        email: String,
        password: String
    ): Either<CustomerLoginModel, LoginErrors> {
        return try {
            val res = authService.login()
            if (res.isSuccessful) {
                val customer = res.body()?.customer?.first() {
                    it?.email.equals(email)
                } ?: return Either.Error(LoginErrors.CustomerNotFound, "CustomerNotFound")
                return if (customer.lastName.equals(password)) {
                    Either.Success(res.body()!!)

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

