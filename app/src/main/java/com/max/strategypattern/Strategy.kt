package com.max.strategypattern

interface PriceStrategy {
    fun getPrice(price: Int): Int
}

class PriceByCreditCard: PriceStrategy {
    override fun getPrice(price: Int): Int {
        println("Will Pay with CreditCard $$price")
        return price
    }
}

class PayByLinePrice(private val isVip: Boolean): PriceStrategy {
    override fun getPrice(price: Int): Int {
        val newPrice = if (isVip) {
            price - 20
        }else {
            price - 10
        }
        println("Will Pay with LinePay $$price")
        return newPrice
    }
}

class PayByGooglePrice: PriceStrategy {
    override fun getPrice(price: Int): Int {
        val newPrice = price - 15
        println("Will Pay with GooglePay $$price")
        return newPrice
    }
}

class Context {

    private var currentStrategy: PriceStrategy? = null

    fun setPayStrategy(strategy: PriceStrategy) {
        currentStrategy = strategy
    }

    fun pay(price: Int) {
        val newPrice = currentStrategy?.getPrice(price)
        println("Pay $newPrice")
    }
}
