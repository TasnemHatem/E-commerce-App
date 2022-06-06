package com.example.e_commerceapp.ui.auth.register.view

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.e_commerceapp.R
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentRegisterBinding
import com.example.e_commerceapp.ui.auth.model.Customer
import com.example.e_commerceapp.ui.auth.model.CustomerModel
import com.example.e_commerceapp.utils.Either
import com.example.e_commerceapp.ui.auth.register.viewModel.RegisterViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

private const val TAG = "RegisterFragment"
@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {
    var firstName: String? = null
    var lastName: String? = null
    var userEmail: String? = null
    var userPassword: String? = null
    var userConfirmPassword: String? = null

    val vm: RegisterViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun afterOnCreateView() {
        super.afterOnCreateView()

        binding.checkoutRegister.setOnClickListener{
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate( R.id.action_registerFragment_to_loginFragment)
        }

        binding.btnRegister.setOnClickListener {

            if (validate()) {
                startProgress()
                val customer = CustomerModel(
                    Customer(
                        firstName = firstName,
                        lastName = lastName,
                        email = userEmail,
                        password = userPassword)
                )
                vm.postData(customer)

                vm.signupState.observe(viewLifecycleOwner) {
                    when(it) {
                        is Either.Success -> {
                            val snackBar = Snackbar.make(
                                binding.root,
                                R.string.registered_successfully,
                                Snackbar.LENGTH_LONG
                            )
                            snackBar.view.setBackgroundColor(Color.GREEN)
                            snackBar.show()
                            endProgress()
                            runBlocking {
                                delay(200)
                                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_registerFragment_to_mainFragment)
                            }
                        }
                        else -> {
                            val snackBar = Snackbar.make(binding.root, getString(R.string.registered_failed), Snackbar.LENGTH_LONG)
                            snackBar.view.setBackgroundColor(Color.RED)
                            snackBar.show()
                            endProgress()
                        }
                    }
                }
            }
        }
        binding.tvLogin.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate( R.id.action_registerFragment_to_loginFragment)

        }
    }
    private fun startProgress(){
        binding.progressBarRegister.visibility = View.VISIBLE
        binding.btnRegister.visibility=View.GONE
    }
    private fun endProgress(){
        binding.progressBarRegister.visibility = View.GONE
        binding.btnRegister.visibility=View.VISIBLE
    }
    private fun validate(): Boolean {
        firstName = binding.etFirstNAmeRegistry.text.toString()
        userEmail = binding.etEmailRegistry.text.toString()
        lastName=binding.etLastNameRegistry.text.toString()
        userPassword = binding.etPasswordRegistry.text.toString()
        userConfirmPassword = binding.etCMpasswordRegistry.text.toString()
        if (userEmail!!.isEmpty()) {
            binding.etEmailRegistry.requestFocus()
            binding.etEmailRegistry.error = getString(R.string.required)
            return false
        }
        if (firstName!!.isEmpty()) {
            binding.etFirstNAmeRegistry.requestFocus()
            binding.etFirstNAmeRegistry.error = getString(R.string.required)
            return false
        }
        if (lastName!!.isEmpty()) {
            binding.etLastNameRegistry.requestFocus()
            binding.etLastNameRegistry.error = getString(R.string.required)
            return false
        }

        if (userPassword!!.isEmpty()) {
            binding.etPasswordRegistry.requestFocus()
            binding.etPasswordRegistry.error = getString(R.string.required)
            return false
        }
        if (userPassword!!.length < 8) {
            binding.etPasswordRegistry.requestFocus()
            binding.etPasswordRegistry.error = getString(R.string.more_than_eight)
            return false
        }
        if (userConfirmPassword!!.length < 8) {
            binding.etCMpasswordRegistry.requestFocus()
            binding.etCMpasswordRegistry.error =  getString(R.string.more_than_eight)
            return false
        }
        if (userConfirmPassword!!.isEmpty()) {
            binding.etCMpasswordRegistry.requestFocus()
            binding.etCMpasswordRegistry.error = getString(R.string.required)
            return false
        }
        if (!userPassword.equals(userConfirmPassword)) {
            binding.etCMpasswordRegistry.requestFocus()
            binding.etCMpasswordRegistry.error = getString(R.string.doesnt_exist)
            return false
        }

        return true
    }
}