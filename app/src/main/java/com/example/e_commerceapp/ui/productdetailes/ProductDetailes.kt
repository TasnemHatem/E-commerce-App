package com.example.e_commerceapp.ui.productdetailes

import android.os.Bundle
import android.view.View
import coil.load
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentProductdetailesBinding
import com.example.e_commerceapp.ui.category.model.Product

class ProductDetailes: BaseFragment<FragmentProductdetailesBinding>(FragmentProductdetailesBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        var product = arguments?.getParcelable<Product>("product")
//        initUI(product)
    }

   private fun initUI(product: Product?){
       binding.productDetailesName.text = product?.title
       binding.productDetailesPrice.text = "US$ ${product?.variants?.get(0)?.price}"
       binding.imageViewProductDetailes.load(product?.image?.src)
    }
}