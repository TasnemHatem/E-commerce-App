package com.example.e_commerceapp.ui.cart.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.e_commerceapp.databinding.ItemCartBinding
import com.example.e_commerceapp.ui.cart.model.LineItemsItem

class CartAdapter(
    val list: List<LineItemsItem>,
    val onCartItemClickListeners: OnCartItemClickListeners,
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(ItemCartBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(list[position],onCartItemClickListeners)
    }

    override fun getItemCount() = list.size

    class CartViewHolder(private val mItem: ItemCartBinding) :
        RecyclerView.ViewHolder(mItem.root) {
        fun bind(item: LineItemsItem,onCartItemClickListeners: OnCartItemClickListeners) {
            mItem.textViewItemPrice.text = item.price
            mItem.imageViewItem.load("https://www.deltamarble.com/wp-content/uploads/woocommerce-placeholder.png")
            mItem.textViewItemName.text = item.name
            mItem.textViewOrderCount.text = item.quantity.toString()
            mItem.imageButtonMinus.setOnClickListener{
                
            }
        }
    }

}