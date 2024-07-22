package com.example.moneyconvert.data.network

import com.example.moneyconvert.data.model.ExchangeResponse
import com.example.moneyconvert.util.Constant.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangeApi {
    @GET("v6/{apiKey}/pair/{base}/{target}/{amountOfCurrency}")
    suspend fun getExchangeValue(
        @Path("apiKey") apiKey: String = API_KEY,
        @Path("base") base: String,
        @Path("target") target: String,
        @Path("amountOfCurrency") amountOfCurrency: Double,
    ): ExchangeResponse
}