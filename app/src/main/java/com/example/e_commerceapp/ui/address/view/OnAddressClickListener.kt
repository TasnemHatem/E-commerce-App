package com.example.e_commerceapp.ui.address.view

import com.example.e_commerceapp.ui.address.model.Address

interface OnAddressClickListener {
    fun clickDelete(userId: Long, addressId: Long)
    fun changeDefault(userId: Long, addressId: Address)
}