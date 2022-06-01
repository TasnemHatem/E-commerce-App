package com.example.e_commerceapp.di

import com.example.e_commerceapp.ui.home.network.VendorService
import com.example.e_commerceapp.ui.home.repo.VendorsRepo
import com.example.e_commerceapp.ui.home.repo.VendorsRepoImpl
import com.example.e_commerceapp.ui.wishlist.network.WishlistService
import com.example.e_commerceapp.ui.wishlist.repo.WishlistRepo
import com.example.e_commerceapp.ui.wishlist.repo.WishlistRepoI
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
    fun getWishlistRepo(wishlistService: WishlistService): WishlistRepo {
        return WishlistRepo(wishlistService)
    }
}