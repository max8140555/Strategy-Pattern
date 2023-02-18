package com.max.strategypattern.example2.strategy.impl

import com.max.strategypattern.example2.Item
import com.max.strategypattern.example2.Item.Companion.isExpired
import com.max.strategypattern.example2.strategy.QualityStrategy

class Ticket80EtcConcertBackstagePassesStrategy: QualityStrategy() {
    override fun updateQuality(item: Item) {
        val newQuality = when {
            item.sellIn >= 11 -> {
                item.quality + 1
            }
            item.sellIn in 6..10 -> {
                item.quality + 2
            }
            else -> {
                item.quality + 3
            }
        }

        item.sellIn = item.sellIn - 1
        item.quality = when {
            item.isExpired() -> {
                lowestQuality
            }
            isHighThanHighestQuality(newQuality) -> {
                highestQuality
            }
            else -> newQuality
        }
    }
}