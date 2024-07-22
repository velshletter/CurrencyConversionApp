package com.example.currencyconverter.data

import com.example.currencyconverter.domain.model.CurrencyResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface CurrencyApi {
    @GET("latest")
    suspend fun getLatestRates(
        @Query("apikey") apiKey: String,
        @Query("currencies") currencies: String,
        @Query("base_currency") baseCurrency: String
    ): CurrencyResponse
}
