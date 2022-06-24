package com.example.e_commerceapp.ui.me.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentOrderRowBinding
import com.example.e_commerceapp.ui.order.model.Order
import java.text.SimpleDateFormat
import java.util.*

class OrderSubAdapter(var context: Context, var data: List<Order>) : RecyclerView.Adapter<OrderSubAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = FragmentOrderRowBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderSubAdapter.ViewHolder {
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
        if(data != null){
            if(data.size <= 2)
                return data.size
            else
                return 2
        }
        else return 0
    }

    fun formatDate(dtStart: String) : String{
        val format = SimpleDateFormat("yyyy-MM-dd")
        val date: Date = format.parse(dtStart)
        val stringFormat = SimpleDateFormat("dd/MM/yyy")
        return stringFormat.format(date)
    }

}