package com.max.strategypattern.example2

class OriginalGildedRose(var items: Array<Item>) {

    fun updateQuality() {
        for (i in items.indices) {
            if (items[i].name != "Aged Brie" && items[i].name != "Backstage passes to a TAFKAL80ETC concert") {
                if (items[i].quality > 0) {
                    when {
                        items[i].name == "Conjured Mana Cake" -> {
                            val newQuality = items[i].quality - 2
                            items[i].quality = if (newQuality < 0) 0 else newQuality
                        }
                        items[i].name != "Sulfuras, Hand of Ragnaros" -> {
                            items[i].quality = items[i].quality - 1
                        }
                        else -> {
                        }
                    }
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1

                    if (items[i].name == "Backstage passes to a TAFKAL80ETC concert") {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1
                            }
                        }
                    }
                }
            }

            if (items[i].name != "Sulfuras, Hand of Ragnaros") {
                items[i].sellIn = items[i].sellIn - 1
            }

            if (items[i].sellIn < 0) {
                if (items[i].name != "Aged Brie") {
                    if (items[i].name != "Backstage passes to a TAFKAL80ETC concert") {
                        if (items[i].quality > 0) {
                            when {
                                items[i].name == "Conjured Mana Cake" -> {
                                    val newQuality = items[i].quality - 2
                                    items[i].quality = if (newQuality < 0) 0 else newQuality
                                }
                                items[i].name != "Sulfuras, Hand of Ragnaros" -> {
                                    items[i].quality = items[i].quality - 1
                                }
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1
                    }
                }
            }
        }
    }
}

