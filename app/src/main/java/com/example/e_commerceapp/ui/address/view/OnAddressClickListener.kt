package com.example.e_commerceapp.ui.address.view

interface OnAddressClickListener {
    fun clickDelete(userId: Long, addressId: Long)
    fun changeDefault(userId: Long, addressId: Long)
}