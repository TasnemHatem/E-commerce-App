package com.example.e_commerceapp.ui.productdetailes

import android.os.Bundle
import android.util.Log
import android.view.View
import coil.load
import com.example.e_commerceapp.R
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentProductdetailesBinding
import com.example.e_commerceapp.ui.category.model.Product
import com.example.e_commerceapp.ui.home.SliderAdapter
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations

class ProductDetailes: BaseFragment<FragmentProductdetailesBinding>(FragmentProductdetailesBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var product = arguments?.getParcelable<Product>("product")
        initUI(product!!)
        addToFavourite()
        addToBag()
    }


    private fun initUI(product: Product){
        val _sliderAdapter = SliderAdapterProductDetailes(product.images)
        binding.imageSliderProductDetailes.setSliderAdapter(_sliderAdapter)
        binding.imageSliderProductDetailes.setIndicatorAnimation(IndicatorAnimationType.WORM)
        binding.imageSliderProductDetailes.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
        binding.imageSliderProductDetailes.startAutoCycle()
        binding.productDetailesName.text = product.title
        binding.productDetailesPrice.text = "US$ ${product?.variants?.get(0)?.price}"
        binding.productDescription.text= product.bodyHtml

    }

    private fun addToFavourite(){
        binding.addToFavouriteFromDetailes.setOnClickListener{
            Log.i("add","addToFavourite")
        }
    }

    private fun addToBag(){
        binding.addToBag.setOnClickListener{
            Log.i("add","addToBag")
        }
    }
}