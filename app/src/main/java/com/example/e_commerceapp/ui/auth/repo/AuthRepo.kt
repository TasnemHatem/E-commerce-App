package com.example.e_commerceapp.ui.auth.repo

import com.example.e_commerceapp.ui.auth.model.*
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface AuthRepo {

    suspend fun register(customer: CustomerModel): Either<CustomerModel, SignUpErrors>

    suspend fun login(email : String , password : String): Either<CustomerLoginModel, LoginErrors>

}