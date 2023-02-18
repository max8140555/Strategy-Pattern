package com.max.strategypattern.example2.strategy

import com.max.strategypattern.example2.Item

class QualityContext {

    private var currentStrategy: QualityStrategy? = null

    fun setQualityStrategy(strategy: QualityStrategy) {
        currentStrategy = strategy
    }

    fun updateQuality(item: Item) {
        currentStrategy?.updateQuality(item)
        currentStrategy = null
    }
}