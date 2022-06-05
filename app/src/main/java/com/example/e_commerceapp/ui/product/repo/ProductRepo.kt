package com.example.e_commerceapp.ui.product.repo

import com.example.e_commerceapp.ui.category.model.ProductsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ProductRepo {
    suspend fun getVendorProducts(vendor: String): Flow<Response<ProductsResponse>>

}