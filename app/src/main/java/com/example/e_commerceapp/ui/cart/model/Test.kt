package com.example.e_commerceapp.ui.cart.model
data class Result(val result: Int, val status: Status)
class Status

fun main(){
    val (result, status) = Result(5 ,Status())
}