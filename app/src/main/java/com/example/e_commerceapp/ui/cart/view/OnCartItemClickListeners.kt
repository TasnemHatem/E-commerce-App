package com.example.e_commerceapp.ui.cart.view

import com.example.e_commerceapp.ui.cart.model.LineItemsItem

interface OnCartItemClickListeners {
    fun onChangeValue()
    fun onClickItem(item: LineItemsItem)
}
