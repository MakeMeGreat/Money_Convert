package com.example.moneyconvert.data.network

import javax.inject.Inject

class NetworkDataSource @Inject constructor(
    private val exchangeAPI: ExchangeApi,
) {
    suspend fun getExchangeValue(baseCode: String, targetCode: String, amountOfCurrency: Double) =
        exchangeAPI.getExchangeValue(
            base = baseCode,
            target = targetCode,
            amountOfCurrency = amountOfCurrency
        )
}