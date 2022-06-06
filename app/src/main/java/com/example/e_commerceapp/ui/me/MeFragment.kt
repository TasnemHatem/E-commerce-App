package com.example.e_commerceapp.ui.me

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.Navigation
import com.example.e_commerceapp.R
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentMeBinding
import com.example.e_commerceapp.local.AppSharedPreference
import com.example.e_commerceapp.utils.Constants.SHARED_NAME
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.text.Typography.dagger

@AndroidEntryPoint
class MeFragment : BaseFragment<FragmentMeBinding>(FragmentMeBinding::inflate) {
    @Inject
    lateinit var appSharedPreference: AppSharedPreference
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.userNameId.text = "Hi, " + appSharedPreference.getStringValue(SHARED_NAME, "")

        if (!appSharedPreference.getBooleanValue("login")) {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_mainFragment_to_loginFragment)
        }

        binding.viewallOrdersBtnId.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_mainFragment_to_orderFragment)
        }

        binding.viewallWishlistBtnId.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_mainFragment_to_wishlistFragment)
        }


    }


}