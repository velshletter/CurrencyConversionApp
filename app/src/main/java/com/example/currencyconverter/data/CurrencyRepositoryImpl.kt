package com.example.currencyconverter.data

import com.example.currencyconverter.domain.interfaces.CurrencyRepository
import com.example.currencyconverter.domain.utils.ResponseState

class CurrencyRepositoryImpl(private val api: CurrencyApi) : CurrencyRepository {
    override suspend fun getLatestRates(apiKey: String, currencies: String, baseCurrency: String): ResponseState {
        return try {
            ResponseState.Success(
                currencyResponse = api.getLatestRates(apiKey, currencies, baseCurrency),
            )
        } catch (e:Exception){
            ResponseState.Error("Проверьте подключение к интернету")
        }
    }
}