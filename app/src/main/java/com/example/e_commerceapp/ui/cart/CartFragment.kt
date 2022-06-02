package com.example.e_commerceapp.ui.cart

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.e_commerceapp.R
import com.example.e_commerceapp.base.LiveDataUtils.observeInFragment
import com.example.e_commerceapp.base.network.DataState
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentCartBinding
import com.example.e_commerceapp.databinding.FragmentCategoryBinding
import com.example.e_commerceapp.ui.cart.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "CartFragment"
@AndroidEntryPoint
class CartFragment : BaseFragment<FragmentCartBinding>(FragmentCartBinding::inflate) {
    val mViewModel: CartViewModel by viewModels()

    override fun afterOnCreateView() {
        super.afterOnCreateView()
        mViewModel.cart.observeInFragment(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                    Log.e(TAG, "afterOnCreateView: "+it)
                    it.data.draftOrder?.lineItems?.filterNotNull()?.let{
                        Log.e(TAG, "afterOnCreateView: $it")
                    }
                }
                is DataState.Error -> {
                    Log.e(TAG, "afterOnCreateView: ")
                }
            }
        }

        mViewModel.requestCart(1091436249323L)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCheckout.setOnClickListener {
            navController.navigate(R.id.action_cartFragment_to_checkoutFragment)
        }
    }
}