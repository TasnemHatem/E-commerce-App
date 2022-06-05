package com.example.e_commerceapp.ui.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.e_commerceapp.R
import com.example.e_commerceapp.ui.category.model.ProductsResponse
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProductAdapter (var product: ProductsResponse,var onProductClickLisenter: OnProductClickLisenter) : RecyclerView.Adapter<ProductAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var current = product.products[position]
        holder.textNameProduct.text = "US$ ${current.variants.get(0).price}"
        holder.image.load(current.image.src)
        holder.adddBtn.setOnClickListener{

        }
        holder.layout.setOnClickListener{
         //   onProductClickLisenter.viewProductDetailes(current)
        }

    }

    fun setListTOAdaptr(_product: ProductsResponse){
        if (_product != null) {
            product = _product
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = product.products.size

    class ViewHolder (val itemView: View): RecyclerView.ViewHolder(itemView){
        val textNameProduct:TextView = itemView.findViewById(R.id.productName_text)
        val image : ImageView = itemView.findViewById(R.id.product_imageview)
        val adddBtn:FloatingActionButton = itemView.findViewById(R.id.add_to_favourit_btn)
        val layout:ConstraintLayout = itemView.findViewById(R.id.product_Layout)


    }
}