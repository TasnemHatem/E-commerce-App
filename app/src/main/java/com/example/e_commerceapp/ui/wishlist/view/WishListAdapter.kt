package com.example.e_commerceapp.ui.wishlist.view

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentMeWhishlistRowBinding
import com.example.e_commerceapp.ui.wishlist.model.DraftOrder
import com.example.e_commerceapp.ui.wishlist.model.LineItem
import com.example.e_commerceapp.ui.wishlist.model.WishlistResponse
import kotlin.reflect.KFunction2


class WishListAdapter(var context: Context, var data: List<LineItem>, var listener: OnWishlistClickListenert) : RecyclerView.Adapter<WishListAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = FragmentMeWhishlistRowBinding.bind(itemView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishListAdapter.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val v: View = inflater.inflate(R.layout.fragment_me_whishlist_row, parent, false)
        val vh: ViewHolder = ViewHolder(v)
        return vh
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(data[position].title == "wishlist") {
            holder.binding.wishlistPriceId.text = data[position].properties[2].value
            holder.binding.wishlistImgId.load(data[position].properties[1].value)
            holder.binding.deleteIconId.setOnClickListener {
                delete(position)
            }
            holder.binding.addCartIconId.setOnClickListener {
                addToCart()
            }
        }

    }

    override fun getItemCount(): Int {
        if(data != null)
            return data.size
        else return 0

    }

    fun delete(position: Int){
        var buildr = AlertDialog.Builder(context)
        buildr.setTitle("Confirm Remove")
        buildr.setMessage("Are you sure you want to remove this item from your wishlist?")
        buildr.setPositiveButton("Yes", DialogInterface.OnClickListener{
                dialog, id ->
            Log.i("TAG", "onBindViewHolder: Remove it")
            listener.clickDeleteListener(data[position])
            dialog.cancel()
        })
        buildr.setNegativeButton("Cancel", DialogInterface.OnClickListener{
                dialog, id ->
            Log.i("TAG", "onBindViewHolder: Don't remove it")
            dialog.cancel()
        })
        var alert = buildr.create()
        alert.show()
    }

    fun addToCart(){

    }
}