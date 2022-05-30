package com.example.e_commerceapp.di

import android.content.Context
import android.content.SharedPreferences
import com.example.e_commerceapp.R
import com.example.e_commerceapp.local.AppSharedPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppSharedPreferencesModule {

    @Singleton
    @Provides
    fun getSharedPreferences(@ApplicationContext context: Context):SharedPreferences{
        return context.getSharedPreferences(
            context.getString(R.string.app_name) + "1"  ,
            Context.MODE_PRIVATE
        )
    }


    @Singleton
    @Provides
    fun providesSharedPreferencesEditor(mSharedPreferences:SharedPreferences) : SharedPreferences.Editor {
        return mSharedPreferences.edit()
    }

    @Singleton
    @Provides
    fun providesSharedPreferencesHelper(sharedPreferences: SharedPreferences,sharedPreferencesEditor:SharedPreferences.Editor): AppSharedPreference {
        return AppSharedPreference(sharedPreferences,sharedPreferencesEditor)
    }
}