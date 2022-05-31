package com.example.e_commerceapp.ui.cart

import android.os.Bundle
import android.view.View
import com.example.e_commerceapp.R
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentCartBinding
import com.example.e_commerceapp.databinding.FragmentCategoryBinding

class CartFragment : BaseFragment<FragmentCartBinding>(FragmentCartBinding::inflate){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCheckout.setOnClickListener{
            navController.navigate(R.id.action_cartFragment_to_checkoutFragment)
        }
    }
}