package com.example.e_commerceapp.ui.category

import android.content.SharedPreferences
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.R
import com.example.e_commerceapp.ui.category.view.OnClickTopFilterListener

class TopTypeProductAdapter (var typeList: List<String>,val onClickTopFilterListener: OnClickTopFilterListener) : RecyclerView.Adapter<TopTypeProductAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopTypeProductAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.topfilter_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopTypeProductAdapter.ViewHolder, position: Int) {
        var current = typeList[position]
        holder.typeText.text = current
        holder.card.setOnClickListener{
            onClickTopFilterListener.filterAll(current)
        }


    }


    override fun getItemCount(): Int = typeList.size

    class ViewHolder (val itemView: View): RecyclerView.ViewHolder(itemView){
        val typeText : TextView = itemView.findViewById(R.id.topfilter_text)
        val card:CardView = itemView.findViewById(R.id.cardView)
    }
}