package com.example.e_commerceapp.ui.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.R
import com.example.e_commerceapp.ui.home.BanerAdapter

class TypeProductAdapter (var typeList: List<String>) : RecyclerView.Adapter<TypeProductAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeProductAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.filter_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TypeProductAdapter.ViewHolder, position: Int) {
        var current = typeList[position]
        holder.typeText.text = current

    }


    override fun getItemCount(): Int = typeList.size

    class ViewHolder (val itemView: View): RecyclerView.ViewHolder(itemView){
        val typeText : TextView = itemView.findViewById(R.id.text_type_category)

    }
}