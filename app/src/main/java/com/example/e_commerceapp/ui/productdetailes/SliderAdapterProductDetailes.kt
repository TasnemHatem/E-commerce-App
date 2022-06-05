package com.example.e_commerceapp.ui.productdetailes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import coil.load
import com.example.e_commerceapp.R
import com.example.e_commerceapp.ui.category.model.Image
import com.example.e_commerceapp.ui.home.SliderAdapter
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapterProductDetailes (var images: List<Image>) : SliderViewAdapter<SliderAdapterProductDetailes.Holder>() {


    override fun onBindViewHolder(viewHolder: Holder, position: Int) {
        viewHolder.imageView.load(images[position].src)
    }

    override fun getCount(): Int {
        return images.size
    }

    inner class Holder(itemView: View) : ViewHolder(itemView) {
        var imageView: ImageView

        init {
            imageView = itemView.findViewById(R.id.image_view_detailes)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?): Holder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.slider_item_detailes,parent,false)
        return Holder(view)
    }
}