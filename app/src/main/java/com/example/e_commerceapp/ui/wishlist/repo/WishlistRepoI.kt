package com.example.e_commerceapp.ui.wishlist.repo

import com.example.e_commerceapp.ui.wishlist.model.DraftOrder
import com.example.e_commerceapp.ui.wishlist.model.DraftOrderResponse
import com.example.e_commerceapp.ui.wishlist.model.WishlistResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body

interface WishlistRepoI {

    suspend fun addWishlist(wishlist: DraftOrderResponse)
    suspend fun addFavouriteProduct(wishlistId: Int, modifiedWishlist: DraftOrderResponse)
    suspend fun getWishlist() : Flow<Response<WishlistResponse>>
    suspend fun deleteFavouriteProduct(wishlistId: Int, modifiedWishlist: DraftOrderResponse)

}