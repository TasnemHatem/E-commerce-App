package com.example.e_commerceapp.di

import com.example.e_commerceapp.BuildConfig.SERVER_URL
import com.example.e_commerceapp.ui.cart.network.CartService
import com.example.e_commerceapp.ui.auth.network.AuthService
import com.example.e_commerceapp.ui.category.network.CategoryService
import com.example.e_commerceapp.ui.home.network.VendorService
import com.example.e_commerceapp.ui.wishlist.network.WishlistService
import com.example.e_commerceapp.ui.product.network.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object ServicesModule {

    @ViewModelScoped
    @Provides
    fun providesVendorService(client: OkHttpClient):VendorService{
        return getDynamicRetrofitClient(client).create(VendorService::class.java)
    }

    @ViewModelScoped
    @Provides
    fun providesAuthService(client: OkHttpClient):AuthService{
        return getDynamicRetrofitClient(client).create(AuthService::class.java)
    }

    @ViewModelScoped
    @Provides
    fun providesCategoryService(client: OkHttpClient): CategoryService {
        return getDynamicRetrofitClient(client).create(CategoryService::class.java)
    }

    @ViewModelScoped
    @Provides
    fun providesCartService(client: OkHttpClient):CartService{
        return getDynamicRetrofitClient(client).create(CartService::class.java)
    }
    
    @ViewModelScoped
    @Provides
    fun providesWishlistService(client: OkHttpClient): WishlistService {
        return getDynamicRetrofitClient(client).create(WishlistService::class.java)
    }
    
    @ViewModelScoped
    @Provides
    fun providesProductService(client: OkHttpClient):ProductService{
        return getDynamicRetrofitClient(client).create(ProductService::class.java)
    }

    private fun getDynamicRetrofitClient(
        client: OkHttpClient,
        baseUrl: String = SERVER_URL,
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}