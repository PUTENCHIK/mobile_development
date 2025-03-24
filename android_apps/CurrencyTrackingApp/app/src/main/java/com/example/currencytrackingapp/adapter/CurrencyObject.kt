package com.example.currencytrackingapp.adapter

import java.time.LocalDateTime

data class CurrencyObject(
    val iconSrc: Int,
    val name: String,
    val label: String,
    var quotation: Float,
    var updated: LocalDateTime
)