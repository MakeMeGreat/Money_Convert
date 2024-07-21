package com.example.moneyconvert.domain

import com.example.moneyconvert.domain.model.ExchangeResponseDomainModel

interface Repository {

    suspend fun getExchangeValue(base: String, target: String, value: String): ExchangeResponseDomainModel
}