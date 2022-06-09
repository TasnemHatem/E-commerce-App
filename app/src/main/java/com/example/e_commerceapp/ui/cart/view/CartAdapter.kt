package com.example.e_commerceapp.ui.cart.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.ItemCartBinding
import com.example.e_commerceapp.ui.cart.model.LineItemsItem
import com.example.e_commerceapp.utils.addOne
import com.example.e_commerceapp.utils.subOneButEnsureNotNegative
import com.example.e_commerceapp.utils.toTwoDecimalDigits

class CartAdapter(
    var list: MutableList<LineItemsItem>,
    private val onCartItemClickListeners: OnCartItemClickListeners,
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(ItemCartBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false), list, onCartItemClickListeners)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(list[position], position)
    }

    override fun getItemCount() = list.size

    class CartViewHolder(
        private val mItem: ItemCartBinding,
        val list: MutableList<LineItemsItem>,
        private val onCartItemClickListeners: OnCartItemClickListeners,
    ) :
        RecyclerView.ViewHolder(mItem.root) {
        fun bind(
            item: LineItemsItem,
            position: Int,
        ) {
            mItem.textViewItemPrice.text =
                mItem.root.context.resources.getString(R.string.price_money,
                    item.price?.toDouble()?.toTwoDecimalDigits().toString())

            mItem.imageViewItem.load(item.properties?.get(0)?.value)
            mItem.textViewItemName.text = item.name
            mItem.textViewOrderCount.text = item.quantity.toString()
            mItem.imageButtonMinus.setOnClickListener {
                showProgress()
                list[position].quantity = list[position].quantity.subOneButEnsureNotNegative()
                if (list[position].quantity == 0){
                    list.removeAt(position)
                }
                onCartItemClickListeners.onChangeValue()
            }
            mItem.imageButtonPlus.setOnClickListener {
                showProgress()
                list[position].quantity = list[position].quantity.addOne()
                onCartItemClickListeners.onChangeValue()
            }
            mItem.root.setOnClickListener {
                onCartItemClickListeners.onClickItem(item)
            }

        }

        private fun showProgress() {
            mItem.quantityLayout.visibility = View.GONE
            mItem.progressBar.visibility = View.VISIBLE
        }
    }

}