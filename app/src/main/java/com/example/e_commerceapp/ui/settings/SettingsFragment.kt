package com.example.e_commerceapp.ui.settings

import android.os.Bundle
import android.view.View
import com.example.e_commerceapp.R
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentSettingsBinding

class SettingsFragment: BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addresBtnId.setOnClickListener {
            navController.navigate(R.id.action_settingsFragment_to_addressFragment)
        }
    }


}