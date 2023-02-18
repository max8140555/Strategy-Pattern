package com.max.strategypattern.example2.strategy.impl

import com.max.strategypattern.example2.Item
import com.max.strategypattern.example2.Item.Companion.isExpired
import com.max.strategypattern.example2.strategy.QualityStrategy

class MinusStrategy(private val quality: Int): QualityStrategy() {
    override fun updateQuality(item: Item) {
        item.sellIn = item.sellIn - 1
        item.quality = if (item.isExpired()) {
            item.quality - (quality * 2)
        } else {
            item.quality - quality
        }.let {
            if (isLessThanLowestQuality(it)) lowestQuality else it
        }
    }
}