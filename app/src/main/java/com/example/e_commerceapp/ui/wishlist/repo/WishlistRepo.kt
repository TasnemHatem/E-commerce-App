package com.example.e_commerceapp.ui.wishlist.repo

import com.example.e_commerceapp.ui.wishlist.model.DraftOrderResponse
import com.example.e_commerceapp.ui.wishlist.network.WishlistService
import kotlinx.coroutines.flow.flow

class WishlistRepo(var wishlistService: WishlistService) : WishlistRepoI {

    override suspend fun addWishlist(wishlist: DraftOrderResponse) {
        wishlistService.addWishlist(wishlist)
    }

    override suspend fun addFavouriteProduct(wishlistId: Int, modifiedWishlist: DraftOrderResponse) {
        wishlistService.addFavouriteProduct(wishlistId, modifiedWishlist)
    }

    override suspend fun getWishlist() = flow {
        emit(wishlistService.getWishlist())
    }

    override suspend fun deleteFavouriteProduct(wishlistId: Long) {
        wishlistService.deleteFavouriteProduct(wishlistId)
    }

}