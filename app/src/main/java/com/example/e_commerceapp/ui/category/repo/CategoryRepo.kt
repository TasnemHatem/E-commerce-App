package com.example.e_commerceapp.ui.category.repo

import com.example.e_commerceapp.ui.category.model.ProductsResponse
import com.example.e_commerceapp.ui.home.model.VendorsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface CategoryRepo {
    suspend fun getProducts(productType: String): Flow<Response<ProductsResponse>>
}