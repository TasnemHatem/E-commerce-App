package com.example.e_commerceapp.ui.category.repo

import com.example.e_commerceapp.ui.category.model.ProductsResponse
import com.example.e_commerceapp.ui.category.network.CategoryService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class CategoryRepoImpl (val categoryService: CategoryService) : CategoryRepo {

    override suspend fun getProducts(productType: String) = flow {
        emit(categoryService.getProducts(productType))
    }.catch {

    }

    override suspend fun getAllProducts() = flow{
        emit(categoryService.getAllProducts())

    }.catch {

    }


}