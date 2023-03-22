package com.max.strategypattern

import com.max.strategypattern.example2.Item
import com.max.strategypattern.example2.strategy.QualityContext
import com.max.strategypattern.example2.strategy.impl.PlusStrategy
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PlusQualityUtilTest {

    private lateinit var qualityContext: QualityContext

    @Before
    fun setup() {
        qualityContext = QualityContext()
    }

    @Test
    fun test_plus_strategy_item_is_not_expired() {
        val plusStrategy = PlusStrategy(1)
        val item = Item("Aged Brie", 2, 0)

        qualityContext.setQualityStrategy(plusStrategy)
        qualityContext.updateQuality(item)

        val expected = Item("Aged Brie", 1, 1)
        Assert.assertEquals(expected.name, item.name)
        Assert.assertEquals(expected.sellIn, item.sellIn)
        Assert.assertEquals(expected.quality, item.quality)
    }

    @Test
    fun test_plus_strategy__item_is_expired() {
        val plusStrategy = PlusStrategy(1)
        val item = Item("Aged Brie", -1, 0)

        qualityContext.setQualityStrategy(plusStrategy)
        qualityContext.updateQuality(item)

        val expected = Item("Aged Brie", -2, 2)
        Assert.assertEquals(expected.name, item.name)
        Assert.assertEquals(expected.sellIn, item.sellIn)
        Assert.assertEquals(expected.quality, item.quality)
    }

    @Test
    fun test_plus_strategy_item_is_high_than_Highest_quality() {
        val plusStrategy = PlusStrategy(1)
        val item = Item("Aged Brie", 2, 50)

        qualityContext.setQualityStrategy(plusStrategy)
        qualityContext.updateQuality(item)

        val expected = Item("Aged Brie", 1, 50)
        Assert.assertEquals(expected.name, item.name)
        Assert.assertEquals(expected.sellIn, item.sellIn)
        Assert.assertEquals(expected.quality, item.quality)
    }
}