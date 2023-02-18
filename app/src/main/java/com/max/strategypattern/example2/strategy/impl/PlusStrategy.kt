package com.max.strategypattern.example2.strategy.impl

import com.max.strategypattern.example2.Item
import com.max.strategypattern.example2.Item.Companion.isExpired
import com.max.strategypattern.example2.strategy.QualityStrategy

class PlusStrategy(private val quality: Int): QualityStrategy() {
    override fun updateQuality(item: Item) {
        item.sellIn = item.sellIn - 1
        item.quality = when {
            item.isExpired() -> {
                item.quality + (quality * 2)
            }
            else -> {
                item.quality + quality
            }
        }.run {
            if (isHighThanHighestQuality(this)) highestQuality else this
        }
    }
}