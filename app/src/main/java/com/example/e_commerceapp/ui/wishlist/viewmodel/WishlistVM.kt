package com.example.e_commerceapp.ui.wishlist.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.local.AppSharedPreference
import com.example.e_commerceapp.ui.wishlist.model.DraftOrder
import com.example.e_commerceapp.ui.wishlist.model.DraftOrderResponse
import com.example.e_commerceapp.ui.wishlist.model.LineItem
import com.example.e_commerceapp.ui.wishlist.model.WishlistResponse
import com.example.e_commerceapp.ui.wishlist.repo.WishlistRepo
import com.example.e_commerceapp.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class WishlistVM @Inject constructor(val wishlistRepo: WishlistRepo) : ViewModel(){

    @Inject
    lateinit var appSharedPreference: AppSharedPreference

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
            var wishlistId = appSharedPreference.getStringValue(Constants.SHARED_FAV_ID, "nothing")
            wishlistRepo.getWishlist(wishlistId).onEach {

                //_wishlist.postValue(it.body()?.draftOrder?.lineItems?.filter { it.title == "wishlist" })
                _wishlist.postValue(it.body()?.draftOrder?.lineItems)

            }.launchIn(viewModelScope)
        }
    }

    fun deleteWish(deletedItem: LineItem){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            Log.i("TAG", "deleteWish: size before${_wishlist.value?.size}")
            Log.i("TAG", "deleteWish: info")
//            _wishlist.postValue(_wishlist.value?.filter { it.properties[0].value != deletedItem.properties[0].value })
            //_wishlist.postValue(_wishlist.value?.filter { it != deletedItem })
            var putWishlist = _wishlist.value?.filter { it != deletedItem }
//            Log.i("TAG", "deleteWish: size after ${_wishlist.value?.size}")
            Log.i("TAG", "deleteWish: size after ${putWishlist?.size}")
            var wishlistId = appSharedPreference.getStringValue(Constants.SHARED_FAV_ID, "nothing")
            wishlistRepo.deleteFavouriteProduct(wishlistId, DraftOrderResponse(DraftOrder(null,null, putWishlist!!)))
            requestWishlist()
        }
    }

    fun addWish(){

    }
}