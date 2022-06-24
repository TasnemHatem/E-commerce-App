package com.example.e_commerceapp.ui.order.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentMeWhishlistRowBinding
import com.example.e_commerceapp.databinding.FragmentOrderRowBinding
import com.example.e_commerceapp.ui.order.model.Order
import com.example.e_commerceapp.ui.wishlist.model.LineItem
import com.example.e_commerceapp.ui.wishlist.view.OnWishlistClickListenert
import java.text.SimpleDateFormat
import java.util.*

class OrderAdapter(var context: Context, var data: List<Order>) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {
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
        data[position].number
        holder.binding.orderId.text = "Order Num: " + data[position].orderNumber
        holder.binding.itemNumId.text = "Num of items: " + data[position].lineItems.size + " items"
        holder.binding.orderTotalId.text = "Total Price: " + data[position].currentTotalPrice +"$"
        holder.binding.orderDateId.text = "Created at: " + formatDate(data[position].createdAt)
    }

    override fun getItemCount(): Int {
        if(data != null)
            return data.size
        else return 0
    }

    fun formatDate(dtStart: String) : String{
        val format = SimpleDateFormat("yyyy-MM-dd")
        val date: Date = format.parse(dtStart)
        val stringFormat = SimpleDateFormat("dd/MM/yyy")
        return stringFormat.format(date)
    }

}