package com.example.e_commerceapp.ui.auth.repo

import com.example.e_commerceapp.ui.auth.model.*
import com.example.e_commerceapp.utils.Either
import com.example.e_commerceapp.utils.LoginErrors
import com.example.e_commerceapp.utils.SignUpErrors

interface AuthRepo {

    suspend fun register(customer: CustomerModel): Either<CustomerModel, SignUpErrors>

    suspend fun login(email : String , password : String): Either<CustomerLoginModel, LoginErrors>

}