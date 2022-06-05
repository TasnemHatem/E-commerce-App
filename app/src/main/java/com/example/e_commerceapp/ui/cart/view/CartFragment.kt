package com.example.e_commerceapp.ui.cart.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.R
import com.example.e_commerceapp.base.LiveDataUtils.observeInFragment
import com.example.e_commerceapp.base.network.DataState
import com.example.e_commerceapp.base.network.NetworkExceptions
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentCartBinding
import com.example.e_commerceapp.databinding.FragmentCategoryBinding
import com.example.e_commerceapp.ui.cart.model.LineItemsItem
import com.example.e_commerceapp.ui.cart.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.NullPointerException

private const val TAG = "CartFragment"

@AndroidEntryPoint
class CartFragment : BaseFragment<FragmentCartBinding>(FragmentCartBinding::inflate),
    OnCartItemClickListeners {
    private val mViewModel: CartViewModel by viewModels()
    private lateinit var mAdapter: CartAdapter
    override fun afterOnCreateView() {
        super.afterOnCreateView()
        mViewModel.cart.observeInFragment(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                    Log.e(TAG, "afterOnCreateView: " + it)
                    binding.apply {
                        viewFlipperState.visibility = View.GONE
                    }
                    it.data.draftOrder?.lineItems?.filterNotNull()?.let {
                        Log.e(TAG, "afterOnCreateView: $it")
                        showData(it)
                    }

                }
                is DataState.Error -> {
                    checkError(it)
                }
                DataState.Idle -> {
                    binding.apply {
                        viewFlipperState.visibility = View.GONE
                    }
                }
                DataState.Loading -> {
                    binding.apply {
                        viewFlipperState.visibility = View.VISIBLE
                        viewFlipperState.displayedChild =
                            viewFlipperState.indexOfChild(loadingLayout.root)
                    }
                }
            }
        }

        mViewModel.requestCart(1091293511915L)
    }

    private fun showData(it: List<LineItemsItem>) {
        binding.apply {
            rvCartListItems.apply {
                mAdapter = CartAdapter(it, this@CartFragment)
                adapter = mAdapter
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                hasFixedSize()
            }
        }
    }

    private fun checkError(it: DataState.Error) {
        when (it.exception) {
            is NetworkExceptions.NetworkConnectionException -> {
                binding.apply {
                    viewFlipperState.visibility = View.VISIBLE
                    viewFlipperState.displayedChild =
                        viewFlipperState.indexOfChild(noNetworkLayout.root)
                }
            }
            is NetworkExceptions.NotAllowedException,
            is NetworkExceptions.UnAuthorizedException,
            -> {
                binding.apply {
                    viewFlipperState.visibility = View.VISIBLE
                    viewFlipperState.displayedChild =
                        viewFlipperState.indexOfChild(unAuthLayout.root)
                }
            }
            else -> {
                binding.apply {
                    viewFlipperState.visibility = View.VISIBLE
                    viewFlipperState.displayedChild =
                        viewFlipperState.indexOfChild(someThingWentWrong.root)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCheckout.setOnClickListener {
            navController.navigate(R.id.action_cartFragment_to_checkoutFragment)
        }
    }

    override fun onClickPlus(item: LineItemsItem) {
        TODO("Not yet implemented")
    }

    override fun onClickMinus(item: LineItemsItem) {
        TODO("Not yet implemented")
    }

    override fun onClickItem(item: LineItemsItem) {
        TODO("Not yet implemented")
    }
}