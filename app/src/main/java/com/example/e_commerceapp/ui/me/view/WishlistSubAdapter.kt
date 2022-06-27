package com.example.e_commerceapp.ui.me.view

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentMeWhishlistRowBinding
import com.example.e_commerceapp.local.AppSharedPreference
import com.example.e_commerceapp.ui.wishlist.model.LineItem
import com.example.e_commerceapp.ui.wishlist.view.OnWishlistClickListenert
import com.example.e_commerceapp.utils.formatCurrency

class WishlistSubAdapter(var context: Context, var data: List<LineItem>, var listener: OnWishlistClickListenert, var appSharedPreference: AppSharedPreference) : RecyclerView.Adapter<WishlistSubAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = FragmentMeWhishlistRowBinding.bind(itemView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistSubAdapter.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val v: View = inflater.inflate(R.layout.fragment_me_whishlist_row, parent, false)
        val vh: ViewHolder = ViewHolder(v)
        return vh
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.binding.wishlistPriceId.text = data[position+1].properties[2].value
        holder.binding.wishlistPriceId.text = formatCurrency(data[position+1].properties[2].value, appSharedPreference)
        holder.binding.wishlistImgId.load(data[position+1].properties[1].value)
        holder.binding.deleteIconId.setOnClickListener {
            delete(position+1)
        }
        holder.binding.addCartIconId.setOnClickListener {
            listener.clickAddToCartListener(data[position+1])
        }
    }

    override fun getItemCount(): Int {
        if(data != null){
            if(data.size <= 5)
                return data.size-1
            else
                return 4
        }
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