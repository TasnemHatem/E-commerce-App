package com.example.e_commerceapp.ui.settings

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import com.example.e_commerceapp.R
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentSettingsBinding
import com.example.e_commerceapp.local.AppSharedPreference
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment: BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate){

    @Inject
    lateinit var appSharedPreference: AppSharedPreference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.currencyBtnId.setOnClickListener {
            navController.navigate(R.id.action_settingsFragment_to_currencyFragment)
        }

        binding.addresBtnId.setOnClickListener {
            navController.navigate(R.id.action_settingsFragment_to_addressFragment)
        }

        binding.loginOutBtn.setOnClickListener {
            appSharedPreference.setValue("login", false)
            navController.navigate(R.id.action_settingsFragment_to_mainFragment)
        }

        binding.btnBack.setOnClickListener{
            navController.navigateUp()
        }

        binding.currencyCodeId.text = appSharedPreference.getStringValue("shared_currency_code")
    }


}