package com.example.e_commerceapp.utils

fun Double.toTwoDecimalDigits(): Double {
    val suspendedDecimalValues = (this * 100).toInt()
    return (suspendedDecimalValues.toDouble() / 100.0)
}

fun Int?.addOne(): Int {
    return (this ?: 0) + 1
}

fun Int?.subOneButEnsureNotNegative(): Int {
    return 0.coerceAtLeast((this ?: 0) - 1)
}