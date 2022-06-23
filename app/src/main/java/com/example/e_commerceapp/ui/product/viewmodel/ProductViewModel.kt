package com.example.e_commerceapp.ui.product.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.local.AppSharedPreference
import com.example.e_commerceapp.ui.category.model.Product
import com.example.e_commerceapp.ui.category.model.ProductsResponse
import com.example.e_commerceapp.ui.product.repo.ProductRepo
import com.example.e_commerceapp.ui.wishlist.model.DraftOrder
import com.example.e_commerceapp.ui.wishlist.model.DraftOrderResponse
import com.example.e_commerceapp.ui.wishlist.model.LineItem
import com.example.e_commerceapp.ui.wishlist.model.Property
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

@HiltViewModel
class ProductViewModel @Inject constructor(
    val productRepo: ProductRepo,
    val wishlistRepo: WishlistRepo,
    val appSharedPreference: AppSharedPreference
) : ViewModel() {

    private val _wishlist: MutableLiveData<List<LineItem>> = MutableLiveData()
    private val _vendorsProduct: MutableLiveData<Response<ProductsResponse>> = MutableLiveData()
    val vendorsProduct: LiveData<Response<ProductsResponse>> = _vendorsProduct
    private val _error: MutableLiveData<Throwable> = MutableLiveData()
    val error: LiveData<Throwable> = _error
    var vendor: String? = null

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _error.postValue(throwable)
        // Log.e(TAG, ": "+throwable.message)
    }

    fun requestVendorsProduct(vendor: String) {
        this.vendor = vendor
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            var wishlistId = appSharedPreference.getLongValue(Constants.SHARED_FAV_ID, 2222)

            wishlistRepo.getWishlist(wishlistId).onEach {wishlistResponse ->
                _wishlist.postValue(wishlistResponse.body()?.draftOrder?.lineItems)
                productRepo.getVendorProducts(vendor).onEach {productlistResponse->
                    mapFavourite(wishlistResponse.body()!!.draftOrder.lineItems, productlistResponse.body()!!.products)
                    _vendorsProduct.postValue(productlistResponse)
                }.launchIn(viewModelScope)
            }.launchIn(viewModelScope)

        }

    }

    fun mapFavourite(wishlist: List<LineItem>, products: List<Product>) {
        for(j in 1 until wishlist.size){
            for(i in 0 until products.size){
                if(products[i].id == (wishlist[j].properties[0].value.toLong()))
                    products[i].isFavourite = true
            }
        }
    }

    fun addFavourite(product: Product){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            var wishlistId = appSharedPreference.getLongValue(Constants.SHARED_FAV_ID, 2222)
            var favData : List<Property> = listOf(Property("productId", product.id.toString()), Property("productImgURL", product.image.src),Property("productPrice", product.variants.get(0).price))
            var favProduct = LineItem(0, "199.00", favData, 1, "wishlist")
            var putWishlist : List<LineItem>? = _wishlist.value!!.plus(favProduct)
            wishlistRepo.addFavouriteProduct(wishlistId, DraftOrderResponse(DraftOrder(null,null, putWishlist!!)))
            requestVendorsProduct(vendor!!)
        }
    }

    fun deleteFavourite(product: Product){
        //Log.i("TAG", "deleteFavourite: profuct id ${product.id}")
        //var s = _wishlist.value?.size
        //_wishlist.value?.map { println(it.properties[0].value) }
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            var wishlistId = appSharedPreference.getLongValue(Constants.SHARED_FAV_ID, 2222)
            Log.i("EMYTAG", "deleteFavourite: before ${_wishlist.value?.size}") //5 -> 4
//            var putWishlist : List<LineItem>? = _wishlist.value?.filter { it.title == "wishlist" }
//            Log.i("EMYTAG", "deleteFavourite: after1 ${putWishlist?.size}")
            //var putWishlist : List<LineItem>? = _wishlist.value?.filter { it.properties[0].value != product.id.toString() }
            var putWishlist = _wishlist.value?.filter {it.properties.isNullOrEmpty() || it.properties[0].value != product.id.toString() }
            //Log.i("EMYTAG", "deleteFavourite: after ${putWishlist?.size}")
            wishlistRepo.deleteFavouriteProduct(wishlistId, DraftOrderResponse(DraftOrder(null,null, putWishlist!!)))
            requestVendorsProduct(vendor!!)
        }
    }

}