package com.max.strategypattern

import com.max.strategypattern.example2.Item
import com.max.strategypattern.example2.strategy.QualityContext
import com.max.strategypattern.example2.strategy.impl.MinusStrategy
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MinusQualityUtilTest {

    private lateinit var qualityContext: QualityContext

    @Before
    fun setup() {
        qualityContext = QualityContext()
    }

    @Test
    fun test_minus_strategy_item_is_not_expired() {
        val minusStrategy = MinusStrategy(1)
        val item = Item("+5 Dexterity Vest", 10, 20)

        qualityContext.setQualityStrategy(minusStrategy)
        qualityContext.updateQuality(item)

        val expected = Item("+5 Dexterity Vest", 9, 19)
        Assert.assertEquals(expected.name, item.name)
        Assert.assertEquals(expected.sellIn, item.sellIn)
        Assert.assertEquals(expected.quality, item.quality)
    }

    @Test
    fun test_minus_strategy__item_is_expired() {
        val minusStrategy = MinusStrategy(1)
        val item = Item("+5 Dexterity Vest", -1, 20)

        qualityContext.setQualityStrategy(minusStrategy)
        qualityContext.updateQuality(item)

        val expected = Item("+5 Dexterity Vest", -2, 18)
        Assert.assertEquals(expected.name, item.name)
        Assert.assertEquals(expected.sellIn, item.sellIn)
        Assert.assertEquals(expected.quality, item.quality)
    }

    @Test
    fun test_plus_strategy_item_is_less_than_lowest_quality() {
        val minusStrategy = MinusStrategy(1)
        val item = Item("+5 Dexterity Vest", 10, 0)

        qualityContext.setQualityStrategy(minusStrategy)
        qualityContext.updateQuality(item)

        val expected = Item("+5 Dexterity Vest", 9, 0)
        Assert.assertEquals(expected.name, item.name)
        Assert.assertEquals(expected.sellIn, item.sellIn)
        Assert.assertEquals(expected.quality, item.quality)
    }
}