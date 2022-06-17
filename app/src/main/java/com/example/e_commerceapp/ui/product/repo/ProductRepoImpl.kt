package com.example.e_commerceapp.ui.product.repo

import com.example.e_commerceapp.ui.category.model.ProductsResponse
import com.example.e_commerceapp.ui.category.network.CategoryService
import com.example.e_commerceapp.ui.product.network.ProductService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class ProductRepoImpl(val productService: ProductService) :ProductRepo{

    override suspend fun getVendorProducts(vendor: String) = flow {
        emit(productService.getVendorProducts(vendor))
    }.catch {  }
}