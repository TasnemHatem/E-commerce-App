package com.example.e_commerceapp.ui.wishlist.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.R
import com.example.e_commerceapp.ui.wishlist.model.DraftOrder
import com.example.e_commerceapp.ui.wishlist.model.WishlistResponse


class WishListAdapter (var context: Context, var data: List<DraftOrder>) : RecyclerView.Adapter<WishListAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishListAdapter.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val v: View = inflater.inflate(R.layout.fragment_me_whishlist_row, parent, false)
        val vh: ViewHolder = ViewHolder(v)
        return vh
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.wishlistPriceId.text = data[0].lineItems[position].productPrice
        holder.binding.wishlistImgId.load(data[0].lineItems[position].productImg)
    }

    override fun getItemCount(): Int {
        if(!data.isEmpty())
            return data[0].lineItems.size
        else return 0
    }
}