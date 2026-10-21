package com.example.lab2

import junit.framework.TestCase.assertEquals
import org.junit.Test

class ConverterTests {
    @Test
    fun testCentimetersConversion() {
        assertEquals(100.0, convertMetersTo(1.0, LengthUnit.CENTIMETER), 0.001)
    }

    @Test
    fun testKilometersConversion() {
        assertEquals(0.001, convertMetersTo(1.0, LengthUnit.KILOMETER), 0.00001)
    }

    @Test
    fun testMilesConversion() {

        val expectedMiles = 1.0 / 1609.344
        assertEquals(expectedMiles, convertMetersTo(1.0, LengthUnit.MILE), 0.0000001)
    }

    @Test
    fun testLightYearsConversion() {
        val expectedLy = 1.0 / 9460730472580800.0
        assertEquals(expectedLy, convertMetersTo(1.0, LengthUnit.LIGHT_YEAR), 1e-18)
    }

    @Test
    fun testFormattingScientificNotation() {
        val formatted = formatResult(1.586e-13)
        assertEquals("1.586E-13", formatted.replace(",", "."))
    }

    @Test
    fun testFormattingStandardNotationLargeNumber() {
        val formatted = formatResult(100000.0)
        val safeFormatted = formatted.replace("\u00A0", " ")

        assertEquals("100 000", safeFormatted)
    }


    @Test
    fun testEmptyInput() {
        val input = ""
        val parsed = input.toDoubleOrNull()
        assertEquals(null, parsed)
    }

    @Test
    fun testNotANumberInput() {
        val input = "abc"
        val parsed = input.toDoubleOrNull()
        assertEquals(null, parsed)
    }
}