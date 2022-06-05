package com.example.e_commerceapp.di

import com.example.e_commerceapp.ui.category.network.CategoryService
import com.example.e_commerceapp.ui.category.repo.CategoryRepo
import com.example.e_commerceapp.ui.category.repo.CategoryRepoImpl
import com.example.e_commerceapp.ui.home.network.VendorService
import com.example.e_commerceapp.ui.home.repo.VendorsRepo
import com.example.e_commerceapp.ui.home.repo.VendorsRepoImpl
import com.example.e_commerceapp.ui.product.network.ProductService
import com.example.e_commerceapp.ui.product.repo.ProductRepo
import com.example.e_commerceapp.ui.product.repo.ProductRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepoModule {

    @ViewModelScoped
    @Provides
    fun getVendorsRepo(vendorService: VendorService):VendorsRepo{
        return VendorsRepoImpl(vendorService)
    }

    @ViewModelScoped
    @Provides
    fun getCategoryRepo(categoryService: CategoryService):CategoryRepo{
        return CategoryRepoImpl(categoryService)
    }

    @ViewModelScoped
    @Provides
    fun getProductRepo(productService: ProductService):ProductRepo{
        return ProductRepoImpl(productService)
    }

}