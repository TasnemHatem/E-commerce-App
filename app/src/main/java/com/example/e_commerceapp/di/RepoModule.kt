package com.example.e_commerceapp.di

import com.example.e_commerceapp.ui.cart.network.CartService
import com.example.e_commerceapp.ui.cart.repo.CartRepo
import com.example.e_commerceapp.ui.cart.repo.CartRepoImpl
import com.example.e_commerceapp.local.AppSharedPreference
import com.example.e_commerceapp.ui.auth.network.AuthService
import com.example.e_commerceapp.ui.auth.repo.AuthRepo
import com.example.e_commerceapp.ui.auth.repo.AuthRepoImpl
import com.example.e_commerceapp.ui.category.network.CategoryService
import com.example.e_commerceapp.ui.category.repo.CategoryRepo
import com.example.e_commerceapp.ui.category.repo.CategoryRepoImpl
import com.example.e_commerceapp.ui.home.network.VendorService
import com.example.e_commerceapp.ui.home.repo.VendorsRepo
import com.example.e_commerceapp.ui.home.repo.VendorsRepoImpl
import com.example.e_commerceapp.ui.wishlist.network.WishlistService
import com.example.e_commerceapp.ui.wishlist.repo.WishlistRepo
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
    fun getAuthRepo(authService: AuthService,appSharedPreference: AppSharedPreference): AuthRepo {
        return AuthRepoImpl(authService,appSharedPreference)
    }

    @ViewModelScoped
    @Provides
    fun getCategoryRepo(categoryService: CategoryService): CategoryRepo {
        return CategoryRepoImpl(categoryService)
    }
    
    @ViewModelScoped
    @Provides
    fun getCartRepo(cartService: CartService):CartRepo{
        return CartRepoImpl(cartService)
    }
    
    @ViewModelScoped
    @Provides
    fun getWishlistRepo(wishlistService: WishlistService): WishlistRepo {
        return WishlistRepo(wishlistService)
    }
    
}