package com.example.lab2

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.abs

enum class LengthUnit(val unitName: String, val symbol: String, val metersInUnit: Double) {
    CENTIMETER("Centimetres", "cm", 0.01),
    KILOMETER("Kilometres", "km", 1000.0),
    MILE("Miles", "mi", 1609.344),
    LIGHT_YEAR("Light years", "ly", 9460730472580800.0)
}

fun convertMetersTo(meters: Double, targetUnit: LengthUnit): Double {
    return meters / targetUnit.metersInUnit
}

fun formatResult(value: Double): String {
    if (value == 0.0) return "0"

    val absValue = abs(value)
    return if (absValue < 0.001 || absValue >= 100_000_000.0)
    {
        val format = NumberFormat.getInstance(Locale.US) as DecimalFormat
        format.applyPattern("0.000E0")
        format.format(value)
    }
    else
    {
        val format = NumberFormat.getInstance(Locale.getDefault())
        format.maximumFractionDigits = 4
        format.format(value)
    }
}