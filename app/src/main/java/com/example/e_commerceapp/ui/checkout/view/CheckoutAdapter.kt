package com.example.e_commerceapp.ui.checkout.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.CheckoutItemBinding
import com.example.e_commerceapp.local.AppSharedPreference
import com.example.e_commerceapp.ui.cart.model.LineItemsItem
import com.example.e_commerceapp.utils.formatCurrency
import com.example.e_commerceapp.utils.toTwoDecimalDigits

class CheckoutAdapter (
    var list: List<LineItemsItem>, val appSharedPreference: AppSharedPreference) : RecyclerView.Adapter<CheckoutAdapter.CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(CheckoutItemBinding.inflate(LayoutInflater.from(parent.context),
            parent, false), list,appSharedPreference)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(list[position], position)
    }

    override fun getItemCount() = list.size

    class CartViewHolder(
        private val mItem: CheckoutItemBinding, val list: List<LineItemsItem>,val appSharedPreference: AppSharedPreference) :
        RecyclerView.ViewHolder(mItem.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: LineItemsItem, position: Int, ) {
            mItem.tvCartItemPrice.text = formatCurrency( item.price.toString(),appSharedPreference)
//                mItem.root.context.resources.getString(
//                    R.string.price_money,
//                    item.price?.toDouble()?.toTwoDecimalDigits().toString())

            mItem.ivCartItemImage.load(item.properties?.get(0)?.value)
            mItem.tvCartItemTitle.text = item.name
            mItem.tvCartItemCount.text = "Count : ${item.quantity.toString()}"
        }

    }

}