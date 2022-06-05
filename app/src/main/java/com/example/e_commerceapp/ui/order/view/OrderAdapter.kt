package com.example.e_commerceapp.ui.order.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentMeWhishlistRowBinding
import com.example.e_commerceapp.databinding.FragmentOrderRowBinding
import com.example.e_commerceapp.ui.wishlist.model.LineItem
import com.example.e_commerceapp.ui.wishlist.view.OnWishlistClickListenert

class OrderAdapter(var context: Context) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = FragmentOrderRowBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderAdapter.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val v: View = inflater.inflate(R.layout.fragment_order_row, parent, false)
        val vh: ViewHolder = ViewHolder(v)
        return vh
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.itemNumId.text = "4 items"
        holder.binding.orderTotalId.text = "765.00 EGP"
        holder.binding.orderDateId.text = "6/6/2022"
    }

    override fun getItemCount(): Int {
//        if(data != null)
//            return data.size
//        else return 0
        return 10
    }
}