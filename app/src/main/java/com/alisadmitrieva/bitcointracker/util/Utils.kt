package com.alisadmitrieva.bitcointracker.util

fun Double.format(digits: Int) = "%.${digits}f".format(this)
