package com.max.strategypattern

import com.max.strategypattern.example2.Item
import com.max.strategypattern.example2.strategy.QualityContext
import com.max.strategypattern.example2.strategy.impl.Ticket80EtcConcertBackstagePassesStrategy
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class Ticket80EtcConcertBackstagePassesQualityUtilTest {

    private lateinit var qualityContext: QualityContext

    @Before
    fun setup() {
        qualityContext = QualityContext()
    }

    @Test
    fun test_ticket__80_etc_strategy_item_is_not_expired_sellIn_high_ten() {
        val strategy = Ticket80EtcConcertBackstagePassesStrategy()

        val item = Item("Backstage passes to a TAFKAL80ETC concert", 15, 20)
        qualityContext.setQualityStrategy(strategy)
        qualityContext.updateQuality(item)

        val expected = Item("Backstage passes to a TAFKAL80ETC concert", 14, 21)
        Assert.assertEquals(expected.name, item.name)
        Assert.assertEquals(expected.sellIn, item.sellIn)
        Assert.assertEquals(expected.quality, item.quality)
    }

    @Test
    fun test_ticket__80_etc_strategy_item_is_not_expired_sellIn_six_in_ten() {
        val strategy = Ticket80EtcConcertBackstagePassesStrategy()

        val item = Item("Backstage passes to a TAFKAL80ETC concert", 10, 30)
        qualityContext.setQualityStrategy(strategy)
        qualityContext.updateQuality(item)

        val expected = Item("Backstage passes to a TAFKAL80ETC concert", 9, 32)
        Assert.assertEquals(expected.name, item.name)
        Assert.assertEquals(expected.sellIn, item.sellIn)
        Assert.assertEquals(expected.quality, item.quality)
    }

    @Test
    fun test_ticket__80_etc_strategy_item_is_not_expired_sellIn_less_six() {
        val strategy = Ticket80EtcConcertBackstagePassesStrategy()

        val item = Item("Backstage passes to a TAFKAL80ETC concert", 5, 35)
        qualityContext.setQualityStrategy(strategy)
        qualityContext.updateQuality(item)

        val expected = Item("Backstage passes to a TAFKAL80ETC concert", 4, 38)
        Assert.assertEquals(expected.name, item.name)
        Assert.assertEquals(expected.sellIn, item.sellIn)
        Assert.assertEquals(expected.quality, item.quality)
    }

    @Test
    fun test_ticket__80_etc_strategy_item_is_expired() {
        val strategy = Ticket80EtcConcertBackstagePassesStrategy()
        val item = Item("Backstage passes to a TAFKAL80ETC concert", 0, 20)

        qualityContext.setQualityStrategy(strategy)
        qualityContext.updateQuality(item)

        val expected1 = Item("Backstage passes to a TAFKAL80ETC concert", -1, 0)
        Assert.assertEquals(expected1.name, item.name)
        Assert.assertEquals(expected1.sellIn, item.sellIn)
        Assert.assertEquals(expected1.quality, item.quality)
    }

    @Test
    fun test_ticket__80_etc_strategy_item_is_high_than_Highest_quality() {
        val strategy = Ticket80EtcConcertBackstagePassesStrategy()
        val item = Item("Backstage passes to a TAFKAL80ETC concert", 3, 49)

        qualityContext.setQualityStrategy(strategy)
        qualityContext.updateQuality(item)

        val expected1 = Item("Backstage passes to a TAFKAL80ETC concert", 2, 50)
        Assert.assertEquals(expected1.name, item.name)
        Assert.assertEquals(expected1.sellIn, item.sellIn)
        Assert.assertEquals(expected1.quality, item.quality)
    }
}