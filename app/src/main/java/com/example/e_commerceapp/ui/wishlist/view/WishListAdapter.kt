package com.example.e_commerceapp.ui.wishlist.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.R
import com.example.e_commerceapp.ui.wishlist.model.DraftOrder
import com.example.e_commerceapp.ui.wishlist.model.WishlistResponse


class WishListAdapter (var context: Context, var data: List<DraftOrder>) : RecyclerView.Adapter<WishListAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


//        lateinit var dayDay: TextView
//        lateinit var dayIcon: ImageView
//        lateinit var dayDesc: TextView
//        lateinit var dayTemp: TextView
//        lateinit var layout: View
//
//        init {
//            layout = itemView
//            dayDay = layout.findViewById(R.id.dailyDayNameId)
//            dayIcon = layout.findViewById(R.id.dailyIconId)
//            dayDesc = layout.findViewById(R.id.dailyDescId)
//            dayTemp = layout.findViewById(R.id.dailyTempId)
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishListAdapter.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val v: View = inflater.inflate(R.layout.fragment_me_whishlist_row, parent, false)
        val vh: ViewHolder = ViewHolder(v)
        return vh
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        if(data.size > 4){
            return 4
        }
        else
            return data.size
    }


}