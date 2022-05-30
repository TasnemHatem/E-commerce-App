package com.example.e_commerceapp.ui.me

import android.os.Bundle
import android.view.View
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentMeBinding
import com.example.e_commerceapp.local.AppSharedPreference
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MeFragment : BaseFragment<FragmentMeBinding>(FragmentMeBinding::inflate){
    @Inject lateinit var appSharedPreference: AppSharedPreference
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }
}