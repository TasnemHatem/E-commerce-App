package com.example.e_commerceapp.ui.address

import android.os.Bundle
import android.view.View
import com.example.e_commerceapp.R
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentAddressBinding

class AddressFragment : BaseFragment<FragmentAddressBinding>(FragmentAddressBinding::inflate){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addAdressBtnId.setOnClickListener{
            navController.navigate(R.id.action_addressFragment_to_newAddressFragment)
        }
    }
}