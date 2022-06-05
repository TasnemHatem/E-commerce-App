package com.example.e_commerceapp.ui.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.e_commerceapp.R
import com.example.e_commerceapp.ui.category.model.ProductsResponse
import com.example.e_commerceapp.ui.category.view.OnClickCategoryListener
import com.example.e_commerceapp.ui.home.model.VendorsResponse

class CategorAdapter(var category: ProductsResponse,val onClickCategoryListener: OnClickCategoryListener) : RecyclerView.Adapter<CategorAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var current = category.products[position]
        holder.txtCategoryName.text = current.title
        holder.image.load(current.image.src)
        holder.layout.setOnClickListener{
         // onClickCategoryListener.viewProductDetailes(current)
        }

    }

    fun setListTOAdaptr(_category: ProductsResponse){
        if (_category != null) {
            category = _category
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = category.products.size

    class ViewHolder (val itemView: View): RecyclerView.ViewHolder(itemView){
        val txtCategoryName : TextView = itemView.findViewById(R.id.category_name)
        val image : ImageView = itemView.findViewById(R.id.category_imageView)
        val layout  :LinearLayoutCompat= itemView.findViewById(R.id.category_Layout)

    }
}