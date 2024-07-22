package com.example.moneyconvert.presentation.mapper

import com.example.moneyconvert.domain.model.ExchangeResponseDomainModel
import com.example.moneyconvert.presentation.model.ExchangeResponseModel
import javax.inject.Inject

class DomainToPresentationMapper @Inject constructor() {

    fun mapToPresentation(domainModel: ExchangeResponseDomainModel) =
        ExchangeResponseModel(
            result = domainModel.result,
            baseCode = domainModel.baseCode,
            targetCode = domainModel.targetCode,
            conversionRate = domainModel.conversionRate,
            conversionResult = domainModel.conversionResult,
        )
}