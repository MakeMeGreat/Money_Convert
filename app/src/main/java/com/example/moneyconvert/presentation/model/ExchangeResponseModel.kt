package com.example.moneyconvert.presentation.model

data class ExchangeResponseModel(
    val result: String,
    val baseCode: String,
    val targetCode: String,
    val conversionRate: Double,
    val conversionResult: Double,
)
