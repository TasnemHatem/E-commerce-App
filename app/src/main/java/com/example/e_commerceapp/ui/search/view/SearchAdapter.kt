package com.example.e_commerceapp.ui.search.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.e_commerceapp.R
import com.example.e_commerceapp.ui.category.model.Product
import com.example.e_commerceapp.ui.category.model.ProductsResponse
import com.example.e_commerceapp.ui.category.view.OnClickCategoryListener

class SearchAdapter (var searchProd: List<Product>, val onClickCategoryListener: OnClickCategoryListener) : RecyclerView.Adapter<SearchAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var current = searchProd[position]
        holder.txtCategoryName.text = current.title
        holder.image.load(current.image.src)
        holder.layout.setOnClickListener{
            // Todo product details
        }
    }

    fun setListTOAdapter(_searchProd: List<Product>){
        if (_searchProd != null) {
            searchProd = _searchProd
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = searchProd.size

    class ViewHolder (private val itemView: View): RecyclerView.ViewHolder(itemView){
        val txtCategoryName : TextView = itemView.findViewById(R.id.category_name)
        val image : ImageView = itemView.findViewById(R.id.category_imageView)
        val layout  : LinearLayoutCompat = itemView.findViewById(R.id.category_Layout)

    }




}