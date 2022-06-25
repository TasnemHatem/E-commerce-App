package com.example.e_commerceapp.ui.newaddress.view

import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.e_commerceapp.base.LiveDataUtils.observeInFragment
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentNewAddressBinding
import com.example.e_commerceapp.local.AppSharedPreference
import com.example.e_commerceapp.ui.address.model.Address
import com.example.e_commerceapp.ui.newaddress.model.PostAddress
import com.example.e_commerceapp.ui.newaddress.viewmodel.NewAddressVM
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern
import javax.inject.Inject

@AndroidEntryPoint
class NewAddressFragment : BaseFragment<FragmentNewAddressBinding>(FragmentNewAddressBinding::inflate){

    @Inject
    lateinit var appSharedPreference: AppSharedPreference

    val viewmodel: NewAddressVM by viewModels()

    //var reqex: String = "^01[0-2]\\s\\d{1,8}\$"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.saveAddressBtnId.setOnClickListener {
            if(validate()){
                Log.i("TAG", "onViewCreated: is validated")
                viewmodel.addAddress(makeAddrees())
            }

        }

        viewmodel.addAddressSuccess.observeInFragment(viewLifecycleOwner){
            Toast.makeText(requireContext(), "Address successfully added", Toast.LENGTH_SHORT).show()
            clearTexts()
            navController.navigateUp()
        }
        viewmodel.addAddressError.observeInFragment(viewLifecycleOwner){
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        binding.btnBack.setOnClickListener {
            navController.navigateUp()
        }
    }

    fun makeAddrees() : PostAddress{
        return PostAddress(Address(binding.address1Id.text.toString(),
            binding.address2Id.text.toString(),
            binding.cityId.text.toString(),
            binding.countryId.text?.replace("\\s".toRegex(), "").toString(),
            binding.defaultSwitchId.isFocused,
            binding.firstNameId.text.toString(),
            null,
            null,
            lastName = binding.lastNameId.text.toString(),
            phone = binding.phoneId.text.toString()
        ))
    }

    private fun validate(): Boolean {

        if(binding.firstNameId.text.toString().isEmpty()) {
            binding.firstNameId.requestFocus()
            binding.firstNameId.error = "Required"
            return false
        }
        if(binding.lastNameId.text.toString().isEmpty()){
            binding.lastNameId.requestFocus()
            binding.lastNameId.error = "Required"
            return false
        }
        var phone = binding.phoneId.text.toString()
        if (binding.phoneId.text.toString().isEmpty()) {
            binding.phoneId.requestFocus()
            binding.phoneId.error = "Required"
            return false
        }

        if(!(phone.length == 11) || !((phone.take(3) == "010") || (phone.take(3) == "011") || (phone.take(3) == "012"))){
            binding.phoneId.requestFocus()
            binding.phoneId.error = "Invalid"
            return false
        }
        if (binding.countryId.text.toString().isEmpty()) {
            binding.countryId.requestFocus()
            binding.countryId.error = "Required"
            return false
        }
        if (binding.cityId.text.toString().isEmpty()) {
            binding.cityId.requestFocus()
            binding.cityId.error = "Required"
            return false
        }
        if (binding.address1Id.text.toString().isEmpty()) {
            binding.address1Id.requestFocus()
            binding.address1Id.error = "Required"
            return false
        }
        if (binding.address2Id.text.toString().isEmpty()) {
            binding.address2Id.requestFocus()
            binding.address2Id.error = "Required"
            return false
        }

        return true

    }

    fun clearTexts(){
        binding.firstNameId.setText("")
        binding.lastNameId.setText("")
        binding.phoneId.setText("")
        binding.countryId.setText("")
        binding.cityId.setText("")
        binding.address1Id.setText("")
        binding.address2Id.setText("")
        binding.defaultSwitchId.isChecked = false
    }
}