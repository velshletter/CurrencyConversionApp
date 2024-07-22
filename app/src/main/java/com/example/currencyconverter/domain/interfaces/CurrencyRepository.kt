package com.example.currencyconverter.domain.interfaces

import com.example.currencyconverter.domain.utils.ResponseState

interface CurrencyRepository {
    suspend fun getLatestRates(apiKey: String, currencies: String, baseCurrency: String): ResponseState
}