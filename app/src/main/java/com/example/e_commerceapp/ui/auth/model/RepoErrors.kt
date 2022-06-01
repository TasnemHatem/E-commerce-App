package com.example.e_commerceapp.ui.auth.model


enum class RepoErrors {
    NoInternetConnection,
    ServerError,
    EmptyBody,
    NullValue,
    NoLoginCustomer,

}
enum class SignUpErrors {
    NoInternetConnection,
    ServerError,
    EmptyBody,
    NullValue,
    NoLoginCustomer,
    EmailAlreadyExist
}


enum class DiscountError{
    NoInternetConnection,
    ServerError,
    EmptyBody,
    DiscountNotFound
}

enum class LoginErrors {
    NoInternetConnection,
    ServerError,
    CustomerNotFound,
    IncorrectEmailOrPassword
}