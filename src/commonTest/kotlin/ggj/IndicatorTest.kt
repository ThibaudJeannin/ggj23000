package ggj

import kotlin.test.Test
import kotlin.test.assertEquals

class IndicatorTest {

    @Test
    fun testIndicator() {
        val indicator = Indicators.Indicator(0.5f, 0.01f)
        indicator.variate()
        assertEquals(0.51f, indicator.value)
    }

    @Test
    fun testIndicatorLowerBound() {
        val indicator = Indicators.Indicator(0.5f, -0.6f)
        indicator.variate()
        assertEquals(0.0f, indicator.value)
    }

    @Test
    fun testIndicatorUpperBound() {
        val indicator = Indicators.Indicator(0.5f, 0.6f)
        indicator.variate()
        assertEquals(1.0f, indicator.value)
    }
}