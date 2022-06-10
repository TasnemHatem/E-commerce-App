package com.example.e_commerceapp.ui.cart.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.R
import com.example.e_commerceapp.base.LiveDataUtils.observeInFragment
import com.example.e_commerceapp.base.network.DataState
import com.example.e_commerceapp.base.network.NetworkExceptions
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.base.utils.safeNavigation
import com.example.e_commerceapp.databinding.FragmentCartBinding
import com.example.e_commerceapp.ui.cart.model.CreateCartBody
import com.example.e_commerceapp.ui.cart.model.DiscountCode
import com.example.e_commerceapp.ui.cart.model.DraftOrder
import com.example.e_commerceapp.ui.cart.model.LineItemsItem
import com.example.e_commerceapp.ui.cart.viewmodel.CartViewModel
import com.example.e_commerceapp.ui.checkout.model.Order
import com.example.e_commerceapp.ui.checkout.model.PostOrderBody
import com.example.e_commerceapp.utils.POST_ORDER_BODY
import com.example.e_commerceapp.utils.toTwoDecimalDigits
import dagger.hilt.android.AndroidEntryPoint


private const val TAG = "CartFragment"

@AndroidEntryPoint
class CartFragment : BaseFragment<FragmentCartBinding>(FragmentCartBinding::inflate),
    OnCartItemClickListeners {

    private var coupon: DiscountCode? = null
    private val mViewModel: CartViewModel by viewModels()
    private val postOrderBody: PostOrderBody = PostOrderBody(Order())
    private lateinit var mAdapter: CartAdapter
    override fun afterOnCreateView() {
        super.afterOnCreateView()
        setupListeners()
        observeViewModel()
    }

    private fun observeViewModel() {
        mViewModel.cart.observeInFragment(viewLifecycleOwner) { data ->
            binding.swipeLayout.isRefreshing = false
            when (data) {
                is DataState.Success -> {
                    Log.e(TAG, "afterOnCreateView: " + data)
                    binding.apply {
                        viewFlipperState.visibility = View.GONE
                        rvCartListItems.visibility = View.VISIBLE
                    }
                    data.data.draftOrder?.lineItems?.filterNotNull()
                        ?.filter { (it.name ?: "") != "lipton" }
                        ?.filterNot { (it.name ?: "") == "lipton" }
                        ?.let {
                            Log.e(TAG, "afterOnCreateView: $data")
                            if (::mAdapter.isInitialized)
                                mAdapter.list.clear()
                            showData(it.toMutableList())
                        }

                }
                is DataState.Error -> {
                    binding.rvCartListItems.visibility = View.GONE
                    checkError(data)
                }
                DataState.Idle -> {
                    binding.apply {
                        viewFlipperState.visibility = View.GONE
                        rvCartListItems.visibility = View.VISIBLE
                    }
                }
                DataState.Loading -> {
                    if (::mAdapter.isInitialized && mAdapter.list.size == 0) {
                        binding.apply {
                            viewFlipperState.visibility = View.VISIBLE
                            viewFlipperState.displayedChild =
                                viewFlipperState.indexOfChild(loadingLayout.root)
                        }
                    }
                }
            }
        }
        mViewModel.requestCart()
        mViewModel.coupon.observeInFragment(viewLifecycleOwner) { data ->
            binding.apply {
                progressBarCoupon.visibility = View.GONE
                btnApplyCoupon.visibility = View.VISIBLE
            }
            when (data) {
                is DataState.Success -> {
                    Log.e(TAG, "afterOnCreateView: " + data)
                    coupon = data.data.discountCode
                    updatePrice()
                    Toast.makeText(context,
                        resources.getString(R.string.coupon_applied_successfully),
                        Toast.LENGTH_SHORT).show()
                }
                is DataState.Error -> {
                    displayMsg(data)
                }
                DataState.Loading -> {

                    binding.apply {
                        progressBarCoupon.visibility = View.VISIBLE
                        btnApplyCoupon.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun displayMsg(it: DataState.Error) {
        when (it.exception) {
            is NetworkExceptions.NetworkConnectionException -> {
                binding.textInputLayoutCoupon.error =
                    resources.getString(R.string.no_network_connection)
            }
            is NetworkExceptions.NotAllowedException,
            is NetworkExceptions.UnAuthorizedException,
            -> {
                binding.apply {
                    textInputLayoutCoupon.error =
                        resources.getString(R.string.try_sign_out_and_signin_again)
                }
            }
            else -> {
                binding.apply {
                    textInputLayoutCoupon.error = resources.getString(R.string.invalid_coupon)
                }
            }
        }

    }

    private fun setupListeners() {
        binding.apply {
            textInputEditText.doOnTextChanged() {
                    _: CharSequence?,
                    _: Int,
                    _: Int,
                    _: Int,
                ->
                textInputLayoutCoupon.error = null
            }
            swipeLayout.setOnRefreshListener {
                mViewModel.requestCart()
            }
            btnBack.setOnClickListener {
                navController.navigateUp()
            }
            btnCheckout.setOnClickListener {

            }
            btnApplyCoupon.setOnClickListener {
                val coupon = textInputEditText.text.toString()
                when {
                    coupon.isEmpty() -> {
                        textInputLayoutCoupon.error = resources.getString(R.string.fill_the_coupon)
                    }
                    else -> {
                        mViewModel.applyCoupon(coupon)
                    }
                }
            }
            binding.btnCheckout.setOnClickListener {
                navController.safeNavigation(R.id.cartFragment,
                    R.id.action_cartFragment_to_addressFragment,
                    Bundle().apply {
                        putParcelable(POST_ORDER_BODY, postOrderBody.apply {
                            this.order?.discountCodes = listOf(coupon?.apply {
                                amount = "10.0"
                            })
                            order?.lineItems = mAdapter.list
                        })
                    })
            }
        }
    }

    private fun showData(it: MutableList<LineItemsItem>) {
        when (it.size) {
            0 -> {
                binding.apply {
                    viewFlipperState.visibility = View.VISIBLE
                    viewFlipperState.displayedChild =
                        viewFlipperState.indexOfChild(noDataLayout.root)
                    btnCheckout.isEnabled = false
                    btnCheckout.isClickable = false
                    textViewTotalPrice.text = ""
                }

            }
            else -> {
                binding.apply {
                    rvCartListItems.apply {
                        mAdapter = CartAdapter(it, this@CartFragment)
                        adapter = mAdapter
                        layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                        hasFixedSize()
                    }
                    btnCheckout.isEnabled = true
                    btnCheckout.isClickable = true
                    updatePrice()
                }
            }
        }
    }

    private fun updatePrice() {
        var totalPrice = 0.0
        mAdapter.list.forEach {
            totalPrice += (it.price ?: "0.0").toDouble() * (it.quantity ?: 0)
        }
        if (coupon != null)
            totalPrice -= 10
        if (totalPrice < 0.0)
            totalPrice = 0.0

        binding.textViewTotalPrice.text =
            resources.getString(R.string.price_money,
                totalPrice.toTwoDecimalDigits().toString())
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


    override fun onChangeValue() {
        val mutableList: MutableList<LineItemsItem?> = mutableListOf<LineItemsItem?>()
        mutableList.addAll(mAdapter.list)
        mViewModel.updateCart(CreateCartBody(DraftOrder(lineItems = mutableList)))
    }


    override fun onClickItem(item: LineItemsItem) {
    }
}