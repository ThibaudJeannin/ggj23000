package ggj

import ggj.indicators.Indicators
import kotlin.test.Test
import kotlin.test.assertEquals

class IndicatorsTest {

    @Test
    fun testIndicators() {
        val indicators = Indicators()
        indicators.bio.value = 0.75f
        indicators.bio.evolution = -0.1f
        indicators.air.value = 0.30f
        indicators.air.evolution = 1.0f
        indicators.soil.value = 0.15f
        indicators.soil.evolution = 0.0f
        indicators.variate()

        assertEquals(0.65f, indicators.bio.value)
        assertEquals(1.0f, indicators.air.value)
        assertEquals(0.15f, indicators.soil.value)
    }
}