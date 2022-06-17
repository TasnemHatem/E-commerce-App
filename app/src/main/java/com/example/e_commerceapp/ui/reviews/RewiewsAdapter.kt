package com.example.e_commerceapp.ui.reviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.R
import com.example.e_commerceapp.ui.reviews.model.Review

class RewiewsAdapter (var reviews: List<Review>) : RecyclerView.Adapter<RewiewsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.review_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var current = reviews[position]
        holder.textname.text = current.name
        holder.textdescription.text = current.description
        holder.rating.rating = current.rating
        //holder.image.setImageResource(current)

    }

    fun setListTOAdaptr(baner: List<Review>?){
        if (baner != null) {
            reviews = baner
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = reviews.size

    class ViewHolder (val itemView: View): RecyclerView.ViewHolder(itemView){
        val textname:TextView = itemView.findViewById(R.id.reviwer_name)
        val textdescription:TextView = itemView.findViewById(R.id.review_descrpition)
        val rating:RatingBar = itemView.findViewById(R.id.rating)
        // val image : ImageView = itemView.findViewById(R.id.imageView_baner)


    }
}