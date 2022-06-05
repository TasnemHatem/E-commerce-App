package com.example.e_commerceapp.ui.product

import com.example.e_commerceapp.ui.category.model.Product

interface OnProductClickLisenter {
    fun viewProductDetailes(product: Product)
    fun addTOFavourite()
}