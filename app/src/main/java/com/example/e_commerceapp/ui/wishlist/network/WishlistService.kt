package com.example.e_commerceapp.ui.wishlist.network

import com.example.e_commerceapp.ui.wishlist.model.DraftOrder
import com.example.e_commerceapp.ui.wishlist.model.DraftOrderResponse
import com.example.e_commerceapp.ui.wishlist.model.WishlistResponse
import retrofit2.Response
import retrofit2.http.*

interface WishlistService {
    @POST("draft_orders.json")
    suspend fun addWishlist(@Body wishlist: DraftOrderResponse)
    //@Query("lat") lat: Double
    @PUT("draft_orders/{wishlistId}.json")
    suspend fun addFavouriteProduct(@Path("wishlistId") wishlistId: Int, @Body modifiedWishlist: DraftOrderResponse)

    @GET("draft_orders.json")
    suspend fun getWishlist(): Response<WishlistResponse>

    @PUT("draft_orders/{wishlistId}.json")
    suspend fun deleteFavouriteProduct(@Path("wishlistId") wishlistId: Long)
}