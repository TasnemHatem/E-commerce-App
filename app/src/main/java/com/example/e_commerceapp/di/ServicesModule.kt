package com.example.e_commerceapp.di

import com.example.e_commerceapp.BuildConfig.SERVER_URL
import com.example.e_commerceapp.ui.address.network.AddressService
import com.example.e_commerceapp.ui.cart.network.CartService
import com.example.e_commerceapp.ui.auth.network.AuthService
import com.example.e_commerceapp.ui.category.network.CategoryService
import com.example.e_commerceapp.ui.currency.network.CurrencyService
import com.example.e_commerceapp.ui.home.network.VendorService
import com.example.e_commerceapp.ui.newaddress.network.NewAddressService
import com.example.e_commerceapp.ui.order.network.OrderService
import com.example.e_commerceapp.ui.wishlist.network.WishlistService
import com.example.e_commerceapp.ui.product.network.ProductService
import com.example.e_commerceapp.utils.Constants.CURRENCY_SERVER_URL
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
    fun providesOrderService(client: OkHttpClient): OrderService {
        return getDynamicRetrofitClient(client).create(OrderService::class.java)
    }

    @ViewModelScoped
    @Provides
    fun providesAddressService(client: OkHttpClient): AddressService {
        return getDynamicRetrofitClient(client).create(AddressService::class.java)
    }

    @ViewModelScoped
    @Provides
    fun providesNewAddressService(client: OkHttpClient):NewAddressService{
        return getDynamicRetrofitClient(client).create(NewAddressService::class.java)
    }

    @ViewModelScoped
    @Provides
    fun providesProductService(client: OkHttpClient):ProductService{
        return getDynamicRetrofitClient(client).create(ProductService::class.java)
    }

    @ViewModelScoped
    @Provides
    fun providesCurrencyService(client: OkHttpClient):CurrencyService{
        return getCurrencyDynamicRetrofitClient(client).create(CurrencyService::class.java)
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

    private fun getCurrencyDynamicRetrofitClient(
        client: OkHttpClient,
        baseUrl: String = CURRENCY_SERVER_URL,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}