package com.example.e_commerceapp.ui.newaddress.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentNewAddressBinding
import com.example.e_commerceapp.local.AppSharedPreference
import com.example.e_commerceapp.ui.address.model.Address
import com.example.e_commerceapp.ui.newaddress.model.PostAddress
import com.example.e_commerceapp.ui.newaddress.viewmodel.NewAddressVM
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewAddressFragment : BaseFragment<FragmentNewAddressBinding>(FragmentNewAddressBinding::inflate){

    @Inject
    lateinit var appSharedPreference: AppSharedPreference

    val viewmodel: NewAddressVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.saveAddressBtnId.setOnClickListener {
            viewmodel.addAddress(makeAddrees())
        }
    }

    fun makeAddrees() : PostAddress{
        return PostAddress(Address(binding.address1Id.text.toString(),
            binding.address2Id.text.toString(),
            binding.cityId.text.toString(),
            binding.countryId.text.toString(),
            binding.defaultSwitchId.isFocused,
            binding.firstNameId.text.toString(),
            null,
            null,
            lastName = binding.lastNameId.text.toString(),
            phone = binding.phoneId.text.toString()
        ))


    }
}