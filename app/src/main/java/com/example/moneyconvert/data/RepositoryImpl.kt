package com.example.moneyconvert.data

import com.example.moneyconvert.data.mapper.DataToDomainMapper
import com.example.moneyconvert.data.network.NetworkDataSource
import com.example.moneyconvert.domain.Repository
import com.example.moneyconvert.domain.model.ExchangeResponseDomainModel
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val mapper: DataToDomainMapper,
) : Repository {
    override suspend fun getExchangeValue(
        baseCode: String, targetCode: String, amountOfCurrency: Double
    ): ExchangeResponseDomainModel {
        return mapper.mapResponseToDomainModel(
            networkDataSource.getExchangeValue(baseCode, targetCode, amountOfCurrency)
        )
    }
}