package com.example.e_commerceapp.ui.payment

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.e_commerceapp.R
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.base.utils.safeNavigation
import com.example.e_commerceapp.databinding.FragmentPaymentBinding
import com.example.e_commerceapp.ui.checkout.model.PostOrderBody
import com.example.e_commerceapp.utils.Constants.PAYPAL_KEY
import com.example.e_commerceapp.utils.POST_ORDER_BODY
import com.paypal.android.sdk.payments.*
import org.json.JSONException
import java.math.BigDecimal

class PaymentFragment : BaseFragment<FragmentPaymentBinding>(FragmentPaymentBinding::inflate) {
    private var postOrderBody: PostOrderBody? = null

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
            navController.safeNavigation(R.id.addressFragment,
                R.id.action_addressFragment_to_paymentFragment,
                Bundle().apply {
                    putParcelable(POST_ORDER_BODY, postOrderBody?.order.apply {

                    })
                })
        }

    }


    /******************paypal***********************************/
    private fun payPalPaymentMethod() {
        var payment =
            PayPalPayment(BigDecimal(100), "USD", "Shopify", PayPalPayment.PAYMENT_INTENT_SALE)
        val intent = Intent(activity, PaymentActivity::class.java)
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, payPalConfiguration)
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment)
        requestPaymentMethod.launch(intent)
    }

    private val requestPaymentMethod =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { data ->
            val resultCode = data.resultCode

            if (resultCode == Activity.RESULT_OK) {
                val confirm =
                    data?.data?.getParcelableExtra<PaymentConfirmation>(PaymentActivity.EXTRA_RESULT_CONFIRMATION)
                if (confirm != null) {
                    try {
                        Log.i(TAG, confirm.toJSONObject().toString(4))
                        Log.i(TAG, confirm.payment.toJSONObject().toString(4))


                    } catch (e: JSONException) {
                        Log.e(TAG, "an extremely unlikely failure occurred: ", e)
                    }

                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i(TAG, "The user canceled.")

            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i(
                    TAG,
                    "An invalid Payment or PayPalConfiguration was submitted. Please see the docs."
                )
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