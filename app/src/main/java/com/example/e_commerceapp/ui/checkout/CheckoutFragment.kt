package com.example.e_commerceapp.ui.checkout

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.e_commerceapp.R
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentCheckoutBinding
import com.example.e_commerceapp.ui.checkout.viewmodel.CheckoutViewModel

class CheckoutFragment : BaseFragment<FragmentCheckoutBinding>(FragmentCheckoutBinding::inflate) {

    val mViewModel:CheckoutViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnPlaceOrder.setOnClickListener {
            
        }
    }
}