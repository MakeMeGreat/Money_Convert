package com.example.moneyconvert.data.mapper

import com.example.moneyconvert.data.model.ExchangeResponse
import com.example.moneyconvert.domain.model.ExchangeResponseDomainModel
import javax.inject.Inject

class DataToDomainMapper @Inject constructor() {

    fun mapResponseToDomainModel(exchangeResponse: ExchangeResponse) =
        ExchangeResponseDomainModel(
            result = exchangeResponse.result,
            baseCode = exchangeResponse.baseCode,
            targetCode = exchangeResponse.targetCode,
            conversionRate = exchangeResponse.conversionRate,
            conversionResult = exchangeResponse.conversionResult,
        )
}