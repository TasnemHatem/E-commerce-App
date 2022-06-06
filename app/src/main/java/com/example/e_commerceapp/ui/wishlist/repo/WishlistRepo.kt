package com.example.e_commerceapp.ui.wishlist.repo

import android.util.Log
import com.example.e_commerceapp.ui.wishlist.model.DraftOrder
import com.example.e_commerceapp.ui.wishlist.model.DraftOrderResponse
import com.example.e_commerceapp.ui.wishlist.network.WishlistService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class WishlistRepo(var wishlistService: WishlistService) : WishlistRepoI {

    override suspend fun addWishlist(wishlist: DraftOrderResponse) {
        wishlistService.addWishlist(wishlist)
    }

    override suspend fun addFavouriteProduct(wishlistId: Long, modifiedWishlist: DraftOrderResponse) {
        wishlistService.addFavouriteProduct(wishlistId, modifiedWishlist)
    }

    override suspend fun getWishlist(id: Long) = flow {
        emit(wishlistService.getWishlist(id))
    }

    override suspend fun deleteFavouriteProduct(wishlistId: Long, modifiedWishlist: DraftOrderResponse) {
        wishlistService.deleteFavouriteProduct(wishlistId, modifiedWishlist)
    }

}