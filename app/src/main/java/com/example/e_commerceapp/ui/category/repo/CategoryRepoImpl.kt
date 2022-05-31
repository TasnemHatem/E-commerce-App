package com.example.e_commerceapp.ui.category.repo

import com.example.e_commerceapp.ui.category.network.CategoryService
import kotlinx.coroutines.flow.flow

class CategoryRepoImpl (val categoryService: CategoryService) : CategoryRepo {

    override suspend fun getProducts(productType: String) = flow {
        emit(categoryService.getProducts(productType))
    }



}