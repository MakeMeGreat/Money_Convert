package com.example.moneyconvert.domain.model

data class ExchangeResponseDomainModel(
    val result: String,
    val baseCode: String,
    val targetCode: String,
    val conversionRate: Double,
    val conversionResult: Double,
)
