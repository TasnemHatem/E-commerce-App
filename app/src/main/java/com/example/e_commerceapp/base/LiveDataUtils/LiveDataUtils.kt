package com.example.e_commerceapp.base.LiveDataUtils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun <T> LiveData<T>.observeInFragment(viewLifecycleOwner : LifecycleOwner , func:(T)->Unit){
    removeObservers(viewLifecycleOwner)
    observe(viewLifecycleOwner,func)
}