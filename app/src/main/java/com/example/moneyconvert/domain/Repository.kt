package com.example.moneyconvert.domain

import com.example.moneyconvert.domain.model.ExchangeResponseDomainModel

interface Repository {

    suspend fun getExchangeValue(
        baseCode: String,
        targetCode: String,
        amountOfCurrency: Double
    ): ExchangeResponseDomainModel
}