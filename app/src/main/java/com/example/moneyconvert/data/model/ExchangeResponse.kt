package com.example.moneyconvert.data.model

import com.squareup.moshi.Json

data class ExchangeResponse(
    val result: String,
    @Json(name = "base_code")
    val baseCode: String,
    @Json(name = "target_code")
    val targetCode: String,
    @Json(name = "conversion_rate")
    val conversionRate: Double,
    @Json(name = "conversion_result")
    val conversionResult: Double,
)
