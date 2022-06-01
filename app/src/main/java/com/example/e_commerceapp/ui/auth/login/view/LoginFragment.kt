package com.example.e_commerceapp.ui.auth.login.view

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import com.example.e_commerceapp.R
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun afterOnCreateView() {
        super.afterOnCreateView()
        binding.tvRegistry.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }


}