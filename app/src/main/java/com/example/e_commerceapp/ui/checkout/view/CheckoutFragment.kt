package com.example.e_commerceapp.ui.checkout.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.R
import com.example.e_commerceapp.base.LiveDataUtils.observeInFragment
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.base.utils.safeNavigation
import com.example.e_commerceapp.databinding.FragmentCheckoutBinding
import com.example.e_commerceapp.local.AppSharedPreference
import com.example.e_commerceapp.ui.cart.model.LineItemsItem
import com.example.e_commerceapp.ui.checkout.model.Order
import com.example.e_commerceapp.ui.checkout.model.PostOrderBody
import com.example.e_commerceapp.ui.checkout.viewmodel.CheckoutViewModel
import com.example.e_commerceapp.utils.Either
import com.example.e_commerceapp.utils.POST_ORDER_BODY
import com.example.e_commerceapp.utils.formatCurrency
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val TAG = "CheckoutFragment"

@AndroidEntryPoint
class CheckoutFragment : BaseFragment<FragmentCheckoutBinding>(FragmentCheckoutBinding::inflate) {

    private lateinit var mAdapter: CheckoutAdapter
    private var orderBody: Order? = null
    private val mViewModel: CheckoutViewModel by viewModels()

    @Inject
    lateinit var appSharedPreference: AppSharedPreference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun afterOnCreateView() {
        super.afterOnCreateView()
        orderBody = arguments?.getParcelable<Order>(POST_ORDER_BODY)
        setView()
        binding.btnPlaceOrder.setOnClickListener {
            showLoading()
            mViewModel.postOrder(PostOrderBody(orderBody))
        }
        binding.checkoutBack.setOnClickListener {
            navController.navigateUp()
        }

    }

    override fun afterOnViewCreated() {
        super.afterOnViewCreated()
        mViewModel.orderResponse.observeInFragment(viewLifecycleOwner) {
            when (it) {
                is Either.Success -> {
                    val snackBar = Snackbar.make(
                        binding.root,
                        R.string.operation_succeeded,
                        Snackbar.LENGTH_LONG
                    )
                    snackBar.view.setBackgroundColor(Color.GREEN)
                    snackBar.show()
                    hideLoading()

                    navController
                        .safeNavigation(
                            R.id.checkoutFragment,
                            R.id.action_checkoutFragment_to_mainFragment
                        )
                }
                else -> {
                    val snackBar = Snackbar.make(
                        binding.root,
                        getString(R.string.operation_failed),
                        Snackbar.LENGTH_LONG
                    )
                    snackBar.view.setBackgroundColor(Color.RED)
                    snackBar.show()
                    hideLoading()
                }
            }
        }

    }

    @SuppressLint("SetTextI18n")
    private fun setView() {
        var subTotal = 0.0
        binding.rvCartListItems.apply {
            mAdapter = CheckoutAdapter(orderBody?.lineItems as List<LineItemsItem>,appSharedPreference)
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            hasFixedSize()
        }
        binding.apply {
            tvCheckoutFullName.text = (orderBody?.shippingAddress?.firstName ?: "Mido") + " " +
                    (orderBody?.shippingAddress?.lastName ?: "Mido")
            tvCheckoutAddress.text = orderBody?.shippingAddress?.country ?: "Egypt"
            tvCheckoutAdditionalNote.text = orderBody?.shippingAddress?.city ?: "cairo"
            tvCheckoutOtherDetails.text = orderBody?.shippingAddress?.address1 ?: "maadi"
            tvCheckoutMobileNumber.text = orderBody?.shippingAddress?.phone ?: "4637485"
//            tvCheckoutShippingCharge.text = formatCurrency("5.0", appSharedPreference)
            for (e in orderBody?.lineItems!!) {
                if (e != null) {
                    subTotal += (e.price?.toDouble() ?: 0.0) * (e.quantity?.toDouble() ?: 0.0)
                }
            }
            Log.e(TAG, "setView: ${orderBody?.discountCodes}")
            tvCheckoutSubTotal.text =
                formatCurrency((subTotal - (if (orderBody?.discountCodes == null || orderBody?.discountCodes?.isEmpty() != false) 0.0 else 10.0 )).toString(),
                    appSharedPreference)
            tvCheckoutTotalAmount.text =
                formatCurrency((subTotal).toString(), appSharedPreference)
            tvPaymentMode.text = orderBody?.gateway ?: "cash"
        }
    }

    private fun showLoading() {
        binding.btnPlaceOrder.visibility = View.GONE
        binding.progressBarCheckout.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.btnPlaceOrder.visibility = View.VISIBLE
        binding.progressBarCheckout.visibility = View.GONE
    }
}