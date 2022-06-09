package com.example.e_commerceapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApp: Application() {
    /*
    override fun onCreate() {
        super.onCreate()
        val config = CheckoutConfig(
            application = this,
            clientId = YOUR_CLIENT_ID,
            environment = Environment.SANDBOX,
            returnUrl = "BuildConfig.APPLICATION_ID":
            currencyCode = CurrencyCode.USD,
        userAction = UserAction.PAY_NOW,
        settingsConfig = SettingsConfig(
            loggingEnabled = true
        )
        )
        PayPalCheckout.setConfig(config)
    }*/
}