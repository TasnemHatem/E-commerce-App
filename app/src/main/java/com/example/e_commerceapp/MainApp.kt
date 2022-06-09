package com.example.e_commerceapp

import android.app.Application
import com.example.e_commerceapp.utils.Constants.PAYPAL_KEY
import com.paypal.checkout.PayPalCheckout
import com.paypal.checkout.config.CheckoutConfig
import com.paypal.checkout.config.Environment
import com.paypal.checkout.config.SettingsConfig
import com.paypal.checkout.createorder.CurrencyCode
import com.paypal.checkout.createorder.UserAction
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApp: Application() {

    override fun onCreate() {
        super.onCreate()
        val config = CheckoutConfig(
            application = this,
            clientId = PAYPAL_KEY,
            environment = Environment.SANDBOX,
            returnUrl = "MainApp://paypalpay",
            currencyCode = CurrencyCode.USD,
        userAction = UserAction.PAY_NOW,
        settingsConfig = SettingsConfig(
            loggingEnabled = true
        )
        )
        PayPalCheckout.setConfig(config)
    }
}