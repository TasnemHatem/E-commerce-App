package com.example.e_commerceapp.utils

import com.example.e_commerceapp.local.AppSharedPreference

fun formatCurrency(current: String, appSharedPreference: AppSharedPreference) : String{
    var value= appSharedPreference.getStringValue("shared_currency_value").toDouble()
    val price = current.toDouble()
    val temp = value* price
    val temp2 = "%.2f".format(temp)
    return appSharedPreference.getStringValue("shared_currency_code") + " " + temp2
}
