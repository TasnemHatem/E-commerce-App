package com.example.e_commerceapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.e_commerceapp.R
import com.example.e_commerceapp.ui.home.model.VendorsResponse

class VendorResponseAdapter (var brands:  VendorsResponse, private val clickBrandBtn: () -> Unit) : RecyclerView.Adapter<VendorResponseAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.brands_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var current = brands.smartCollections[position]
       holder.txtBrandName.text = current.title
       holder.image.load(current.image.src)
        holder.image.setOnClickListener{
            clickBrandBtn()
        }

    }

    fun setListTOAdaptr(brand: VendorsResponse){
        if (brand != null) {
            brands = brand
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = brands.smartCollections.size

    class ViewHolder (val itemView: View): RecyclerView.ViewHolder(itemView){
        val txtBrandName : TextView = itemView.findViewById(R.id.brand_name_text)
       val image : ImageView = itemView.findViewById(R.id.imageView_brand)

    }
}

//data class brand(var name:String,var image:Int)