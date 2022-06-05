package com.example.e_commerceapp.di

import com.example.e_commerceapp.BuildConfig.API_KEY
import com.example.e_commerceapp.BuildConfig.API_PASS


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val NETWORK_TIMEOUT = 5000L

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHeadersInterceptor() =
        Interceptor { chain ->
            chain.proceed(
                chain.request().newBuilder()
                    .build()
            )
        }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        headersInterceptor: Interceptor,
        logging: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(headersInterceptor)
            .addInterceptor(logging)
            .addInterceptor(::authInterceptor)
            .build()

    }

    private fun authInterceptor(chain: Interceptor.Chain): Response {
        val credentials = Credentials.basic(API_KEY, API_PASS);
        return chain.proceed(
            chain.request().newBuilder().header("Authorization", credentials)
                .build()
        )
    }


}