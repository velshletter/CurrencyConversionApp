package com.example.currencyconverter.domain.usecase

import com.example.currencyconverter.AppConstants.API_KEY
import com.example.currencyconverter.domain.interfaces.CurrencyRepository
import com.example.currencyconverter.domain.utils.ResponseState

class ConvertUseCase(private val repository: CurrencyRepository) {

    suspend fun invoke(sum: String, curTo: String, curFrom: String): ResponseState {
        return if (sum.isEmpty()) {
            ResponseState.Error(message = "Введите сумму для конвертации")
        } else {
            val response = repository.getLatestRates(API_KEY, curTo, curFrom)
            if (response is ResponseState.Success) {

                val ratio = response.currencyResponse.data[curTo]!!
                val convertedSum = (sum.toDouble() * ratio).toString()
                ResponseState.Success(response.currencyResponse, convertedSum)

            } else {
                response
            }
        }
    }
}