package com.example.moneyconvert.domain.usecase

import com.example.moneyconvert.domain.Repository
import javax.inject.Inject

class GetExchangeResponseUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(baseCode: String, targetCode: String, amountOfCurrency: Double) =
        repository.getExchangeValue(baseCode, targetCode, amountOfCurrency)
}