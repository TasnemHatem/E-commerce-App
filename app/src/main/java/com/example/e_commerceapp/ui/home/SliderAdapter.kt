package com.example.e_commerceapp.ui.home



import com.smarteist.autoimageslider.SliderViewAdapter

import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.ImageView
import com.example.e_commerceapp.R


class SliderAdapter(var images: IntArray) : SliderViewAdapter<SliderAdapter.Holder>() {


    override fun onBindViewHolder(viewHolder: Holder, position: Int) {
        viewHolder.imageView.setImageResource(images[position])
    }

    override fun getCount(): Int {
        return images.size
    }

    inner class Holder(itemView: View) : ViewHolder(itemView) {
        var imageView: ImageView

        init {
            imageView = itemView.findViewById(R.id.image_view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?): Holder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.slider_item,parent,false)
        return Holder(view)
    }
}