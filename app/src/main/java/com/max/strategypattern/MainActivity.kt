package com.max.strategypattern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.max.strategypattern.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val price = 100
        var newPrice = price

        // 初始
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            newPrice = when (checkedId) {
                R.id.button_credit_card -> getCreditCardPrice(price)
                R.id.button_line_pay -> getLinePayPrice(price)
                R.id.button_google_pay -> getGooglePayPrice(price)
                else -> price
            }
        }
        binding.buttonPay.setOnClickListener {
            pay(newPrice)
        }

        // 使用簡單工廠模式
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val type = when (checkedId) {
                R.id.button_credit_card -> PayType.CREDIT_CARD
                R.id.button_line_pay -> PayType.LINE_PAY
                R.id.button_google_pay -> PayType.GOOGLE_PAY
                else -> return@setOnCheckedChangeListener
            }
            newPrice = selectedPayType(type, price)
        }
        binding.buttonPay.setOnClickListener {
            pay(newPrice)
        }

        // 使用策略模式
        val context = Context()
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val strategy = when (checkedId) {
                R.id.button_credit_card -> PriceByCreditCard()
                R.id.button_line_pay -> PayByLinePrice()
                R.id.button_google_pay -> PayByGooglePrice()
                else -> return@setOnCheckedChangeListener
            }
            context.setPayStrategy(strategy)
        }
        binding.buttonPay.setOnClickListener {
            context.pay(price)
        }
    }

    enum class PayType {
        CREDIT_CARD,
        LINE_PAY,
        GOOGLE_PAY
    }

    private fun selectedPayType(payType: PayType, price: Int): Int {
        return when (payType) {
            PayType.CREDIT_CARD -> getCreditCardPrice(price)
            PayType.LINE_PAY -> getLinePayPrice(price)
            PayType.GOOGLE_PAY -> getGooglePayPrice(price)
        }
    }

    private fun getCreditCardPrice(price: Int): Int {
        println("Will Pay with CreditCard $$price")
        return price
    }

    private fun getLinePayPrice(price: Int): Int {
        val newPrice = price - 10
        println("Will Pay with CreditCard $$price")
        return newPrice
    }

    private fun getGooglePayPrice(price: Int): Int {
        val newPrice = price - 15
        println("Will Pay with GooglePay $$price")
        return newPrice
    }

    private fun pay(price: Int) {
        println("Pay $price")
    }
}