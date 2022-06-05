package com.example.e_commerceapp.ui.cart.view

import com.example.e_commerceapp.ui.cart.model.LineItemsItem

interface OnCartItemClickListeners {
    fun onClickPlus(item: LineItemsItem)
    fun onClickMinus(item: LineItemsItem)
    fun onClickItem(item: LineItemsItem)
}
