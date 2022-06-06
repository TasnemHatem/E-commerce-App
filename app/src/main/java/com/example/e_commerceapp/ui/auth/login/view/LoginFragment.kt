package com.example.e_commerceapp.ui.auth.login.view

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.Navigation
import com.example.e_commerceapp.R
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentLoginBinding
import com.example.e_commerceapp.local.AppSharedPreference
import com.example.e_commerceapp.ui.auth.login.viewModel.LoginViewModel
import com.example.e_commerceapp.utils.Either
import com.example.e_commerceapp.utils.LoginErrors
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


private const val TAG = "LoginFragment"
@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    var userEmail: String? = null
    var userPassword: String? = null

    val vm: LoginViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun afterOnCreateView() {
        super.afterOnCreateView()
        binding.tvRegistry.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.backLogin.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate( R.id.action_loginFragment_to_mainFragment)

        }

        binding.btnLogin.setOnClickListener {
            if (validate()) {
                startProgress()
                vm.getData(userEmail!!,userPassword!!)
                vm.loginState.observe(viewLifecycleOwner) {
                    when (it) {
                        is Either.Error -> when (it.errorCode) {
                            LoginErrors.NoInternetConnection -> {
                                val snackBar = Snackbar.make(binding.root, "NoInternetConnection" + it.message, Snackbar.LENGTH_LONG)
                                snackBar.view.setBackgroundColor(Color.RED)
                                snackBar.show()
                                endProgress()
                            }
                            LoginErrors.ServerError -> {
                                val snackBar = Snackbar.make(binding.root, "ServerError" + it.message, Snackbar.LENGTH_LONG)
                                snackBar.view.setBackgroundColor(Color.RED)
                                snackBar.show()
                                endProgress()

                            }
                            LoginErrors.IncorrectEmailOrPassword->{
                                val snackBar = Snackbar.make(binding.root, R.string.incorrect_email_or_password, Snackbar.LENGTH_LONG)
                                snackBar.view.setBackgroundColor(Color.RED)
                                snackBar.show()
                                endProgress()
                            }
                        }
                        is Either.Success -> {
                            val snackBar = Snackbar.make(binding.root, R.string.login_successfully, Snackbar.LENGTH_LONG)
                            snackBar.view.setBackgroundColor(Color.GREEN)
                            snackBar.show()
                            endProgress()
                            runBlocking {
                                delay(200)
                                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate( R.id.action_loginFragment_to_mainFragment)
                            }

                        }
                    }
                }
            }
        }

    }



    private fun startProgress(){
        binding.progressBarLogin.visibility = View.VISIBLE
        binding.btnLogin.visibility=View.GONE
    }
    private fun endProgress(){
        binding.progressBarLogin.visibility = View.GONE
        binding.btnLogin.visibility=View.VISIBLE
    }

    private fun validate(): Boolean {
        userEmail = binding.etEmailLogin.text.toString()
        userPassword = binding.etPasswordLogin.text.toString()
        if (userEmail!!.isEmpty()) {
            binding.etEmailLogin.requestFocus()
            binding.etEmailLogin.error = "Required"
            return false
        }
        if (userPassword!!.isEmpty()) {
            binding.etPasswordLogin.requestFocus()
            binding.etPasswordLogin.error = "Required"
            return false
        }
        return true

    }




}