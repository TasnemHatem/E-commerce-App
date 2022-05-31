package com.example.e_commerceapp.di

import com.example.e_commerceapp.BuildConfig.SERVER_URL
import com.example.e_commerceapp.ui.home.network.VendorService
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