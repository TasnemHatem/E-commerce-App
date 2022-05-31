package com.example.e_commerceapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.R
import com.example.e_commerceapp.ui.home.model.SmartCollection

class VendorResponseAdapter (var brands: List<SmartCollection>) : RecyclerView.Adapter<VendorResponseAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VendorResponseAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: VendorResponseAdapter.ViewHolder, position: Int) {
        var current = brands[position]
        holder.txtSportName.text = current.title
        //holder.image.setImageResource(current.image)

    }

    fun setListTOAdaptr(brand: List<SmartCollection>?){
        if (brand != null) {
            brands = brand
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = brands.size

    class ViewHolder (val itemView: View): RecyclerView.ViewHolder(itemView){
        val txtSportName : TextView = itemView.findViewById(R.id.category_name)
        val image :ImageView = itemView.findViewById(R.id.category_imageView)


    }
}

//data class brand(var name:String,var image:Int)