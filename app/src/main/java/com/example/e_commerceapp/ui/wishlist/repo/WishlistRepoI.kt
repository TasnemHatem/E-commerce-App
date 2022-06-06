package com.example.e_commerceapp.ui.wishlist.repo

import com.example.e_commerceapp.ui.wishlist.model.DraftOrder
import com.example.e_commerceapp.ui.wishlist.model.DraftOrderResponse
import com.example.e_commerceapp.ui.wishlist.model.WishlistResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body

interface WishlistRepoI {

    suspend fun addWishlist(wishlist: DraftOrderResponse)
    suspend fun addFavouriteProduct(wishlistId: Long, modifiedWishlist: DraftOrderResponse)
    suspend fun getWishlist(id: Long = 1091599171819): Flow<Response<DraftOrderResponse>>
    suspend fun deleteFavouriteProduct(wishlistId: Long, modifiedWishlist: DraftOrderResponse)

}