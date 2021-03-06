package com.example.e_commerceapp.ui.payment

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.e_commerceapp.R
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.base.utils.safeNavigation
import com.example.e_commerceapp.databinding.FragmentPaymentBinding
import com.example.e_commerceapp.ui.checkout.model.CASH
import com.example.e_commerceapp.ui.checkout.model.PAYPAL
import com.example.e_commerceapp.ui.checkout.model.PostOrderBody
import com.example.e_commerceapp.utils.Constants.PAYPAL_KEY
import com.example.e_commerceapp.utils.Constants.SHARED_CURRENCY_CODE
import com.example.e_commerceapp.utils.POST_ORDER_BODY
import com.paypal.android.sdk.payments.*
import com.paypal.checkout.approve.OnApprove
import com.paypal.checkout.createorder.CreateOrder
import com.paypal.checkout.createorder.CurrencyCode
import com.paypal.checkout.createorder.OrderIntent
import com.paypal.checkout.createorder.UserAction
import com.paypal.checkout.order.Amount
import com.paypal.checkout.order.AppContext
import com.paypal.checkout.order.Order
import com.paypal.checkout.order.PurchaseUnit
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONException
import java.math.BigDecimal
import javax.inject.Inject

@AndroidEntryPoint
class PaymentFragment : BaseFragment<FragmentPaymentBinding>(FragmentPaymentBinding::inflate) {
    private var postOrderBody: PostOrderBody? = null

    @Inject
    lateinit var appSharedPreferences: SharedPreferences
    override fun afterOnCreateView() {
        super.afterOnCreateView()
        postOrderBody = arguments?.getParcelable<PostOrderBody>(POST_ORDER_BODY)
        setUpPayPal()
        binding.backPayment.setOnClickListener {
            navController.navigateUp()
        }
        binding.lyCardVisa.setOnClickListener {
            payPalPaymentMethod()
        }

        binding.lyCardCasht.setOnClickListener {
            navController.safeNavigation(R.id.paymentFragment,
                R.id.action_paymentFragment_to_checkoutFragment,
                Bundle().apply {
                    putParcelable(POST_ORDER_BODY, postOrderBody?.order?.apply {
                        gateway = CASH
                    })
                })
        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    /******************paypal***********************************/
    private fun payPalPaymentMethod() {
        val price = ((postOrderBody?.order?.lineItems?.map { it?.price?.toDouble() ?: 0.0 }
            ?.reduce { x, y -> x + y }
            ?: 0.0) - (if (postOrderBody?.order?.discountCodes == null ||postOrderBody?.order?.discountCodes?.isEmpty() != false) 0.0 else 10.0 )).toBigDecimal()
        val currencyCode = appSharedPreferences.getString(SHARED_CURRENCY_CODE, "USD")
        var payment =
            PayPalPayment(price, currencyCode, "Shopify", PayPalPayment.PAYMENT_INTENT_SALE)
        val intent = Intent(activity, PaymentActivity::class.java)
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, payPalConfiguration)
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment)
        requestPaymentMethod.launch(intent)
    }

    private val requestPaymentMethod =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { data ->
            val resultCode = data.resultCode

            if (resultCode == Activity.RESULT_OK) {
                val auth =
                    data?.data?.getParcelableExtra<PayPalAuthorization>(PayPalProfileSharingActivity.EXTRA_RESULT_AUTHORIZATION)
                val confirm =

                    data?.data?.getParcelableExtra<PaymentConfirmation>(PaymentActivity.EXTRA_RESULT_CONFIRMATION)
                if (confirm != null) {
                    try {
                        Log.e(TAG, ": auth ${auth}")
                        Log.i(TAG, confirm.toJSONObject().toString(4))
                        Log.i(TAG, confirm.payment.toJSONObject().toString(4))
                        navController.safeNavigation(R.id.paymentFragment,
                            R.id.action_paymentFragment_to_checkoutFragment,
                            Bundle().apply {
                                putParcelable(POST_ORDER_BODY, postOrderBody?.order?.apply {
                                    gateway = PAYPAL
                                })
                            })
                    } catch (e: JSONException) {
                        Log.e(TAG, "an extremely unlikely failure occurred: ", e)
                        Toast.makeText(context,
                            "Payment failed please try Again!",
                            Toast.LENGTH_SHORT).show()
                    }

                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i(TAG, "The user canceled.")
                Toast.makeText(context, "Payment Canceled!", Toast.LENGTH_SHORT).show()

            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i(
                    TAG,
                    "An invalid Payment or PayPalConfiguration was submitted. Please see the docs."
                )
                Toast.makeText(context, "Invalid Payment Data!", Toast.LENGTH_SHORT).show()
            }

        }
    private val payPalConfiguration =
        PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_NO_NETWORK)
            .clientId(PAYPAL_KEY)


    private fun setUpPayPal() {
        var intent = Intent(activity, PayPalService::class.java)
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, payPalConfiguration)
        this.requireActivity().startService(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        this.requireActivity().stopService(Intent(activity, PayPalService::class.java))
    }
}