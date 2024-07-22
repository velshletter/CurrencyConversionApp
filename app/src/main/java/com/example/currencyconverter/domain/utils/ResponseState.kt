package com.example.currencyconverter.domain.utils

import com.example.currencyconverter.domain.model.CurrencyResponse

sealed class ResponseState{
    data class Success(val currencyResponse: CurrencyResponse, val convertedSum: String = "") : ResponseState()
    data object Loading: ResponseState()
    data object Empty: ResponseState()
    data class Error(val message: String): ResponseState()
}