package com.kosigo.payments

import java.text.DecimalFormat
import java.text.NumberFormat

fun Double.toDecimalString(): String {
    val numberFormat = NumberFormat.getInstance()
    (numberFormat as? DecimalFormat)?.applyPattern("#,##0.00")
    return numberFormat.format(this)
}