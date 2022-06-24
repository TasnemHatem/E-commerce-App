package com.example.e_commerceapp.ui.wishlist.view

import com.example.e_commerceapp.ui.wishlist.model.DraftOrderResponse
import com.example.e_commerceapp.ui.wishlist.model.LineItem

interface OnWishlistClickListenert   {
    fun clickDeleteListener(deletedItem: LineItem)
    fun clickAddToCartListener(deletedItem: LineItem)
}