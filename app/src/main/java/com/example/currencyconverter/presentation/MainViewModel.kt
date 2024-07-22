package com.example.currencyconverter.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.AppConstants
import com.example.currencyconverter.domain.usecase.ConvertUseCase
import com.example.currencyconverter.domain.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val convertUseCase: ConvertUseCase
) : ViewModel() {

    private var _sumFlow = MutableStateFlow("")
    val sumFlow: StateFlow<String> get() = _sumFlow

    private var _curFromFlow = MutableStateFlow(AppConstants.currencies[0])
    val curFromFlow: StateFlow<String> get() = _curFromFlow

    private var _curToFlow = MutableStateFlow(AppConstants.currencies[1])
    val curToFlow: StateFlow<String> get() = _curToFlow

    private var _conversedSumFlow = MutableStateFlow("")
    val conversedSumFlow: StateFlow<String> get() = _conversedSumFlow

    private var _responseStateFlow = MutableStateFlow<ResponseState>(ResponseState.Empty)
    val responseStateFlow: StateFlow<ResponseState> get() = _responseStateFlow


    fun convert() {
        viewModelScope.launch {
            _responseStateFlow.value = ResponseState.Loading
            val result = convertUseCase.invoke(sumFlow.value, curToFlow.value, curFromFlow.value)
            _responseStateFlow.value = result
            if (result is ResponseState.Success) {
                _conversedSumFlow.value = result.convertedSum
            }
        }
    }

    fun swapCurrencies(){
        val cur = curToFlow.value
        _curToFlow.value = curFromFlow.value
        _curFromFlow.value = cur
    }

    fun clearResponseState() {
        _responseStateFlow.value = ResponseState.Empty
    }

    fun updateSum(newValue: String) {
        val filteredValue = newValue
            .replace(',', '.')
            .filter { it.isDigit() || it == '.' }
        if (filteredValue.count { it == '.' } <= 1) {
            _sumFlow.value = filteredValue
        }
    }

    fun changeCurFrom(currency: String) {
        _curFromFlow.value = currency
    }

    fun changeCurTo(currency: String) {
        _curToFlow.value = currency
    }

}