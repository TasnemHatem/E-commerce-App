package com.example.e_commerceapp.ui.wishlist.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.ui.wishlist.model.DraftOrder
import com.example.e_commerceapp.ui.wishlist.model.DraftOrderResponse
import com.example.e_commerceapp.ui.wishlist.model.LineItem
import com.example.e_commerceapp.ui.wishlist.model.WishlistResponse
import com.example.e_commerceapp.ui.wishlist.repo.WishlistRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class WishlistVM @Inject constructor(val wishlistRepo: WishlistRepo) : ViewModel(){
    private val _wishlist: MutableLiveData<List<LineItem>> = MutableLiveData()
    val wishlist: LiveData<List<LineItem>> =_wishlist

    private val _error: MutableLiveData<Throwable> = MutableLiveData()
    val error: LiveData<Throwable> =_error

    private val coroutineExceptionHandler= CoroutineExceptionHandler { _, throwable ->
        _error.postValue(throwable)
        Log.e("TAG", ": "+throwable.message)
    }

    fun requestWishlist(){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            wishlistRepo.getWishlist().onEach {
                //it.body()?.draftOrders?.filter { it.isWishlist }

                //_wishlist.postValue(it.body()?.draftOrders?.filter { it.isWishlist })
                //Log.i("TAG", "requestWishlist: ${it.body()}")
                _wishlist.postValue(it.body()?.draftOrder?.lineItems?.filter { it.title != "bad" })


            }.launchIn(viewModelScope)
        }
    }

    fun deleteWish(id: Long){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            wishlistRepo.deleteFavouriteProduct(id)
        }
    }
}