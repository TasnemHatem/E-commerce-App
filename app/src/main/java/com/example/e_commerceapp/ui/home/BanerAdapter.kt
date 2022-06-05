package com.example.e_commerceapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.R

class  BanerAdapter (var baners: List<Int>) : RecyclerView.Adapter<BanerAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.advertise_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var current = baners[position]
        holder.image.setImageResource(current)

    }

    fun setListTOAdaptr(baner: List<Int>?){
        if (baner != null) {
            baners = baner
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = baners.size

    class ViewHolder (val itemView: View): RecyclerView.ViewHolder(itemView){
        val image : ImageView = itemView.findViewById(R.id.imageView_baner)


    }
}