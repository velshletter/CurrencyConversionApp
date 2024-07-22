package com.example.currencyconverter.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.currencyconverter.R
import com.example.currencyconverter.domain.utils.ResponseState
import com.example.currencyconverter.presentation.ui.theme.CurrencyConversionAppTheme


import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CurrencyConversionAppTheme {
                Screen(viewModel)
                ResponseObserver()
            }
        }

    }

    @Composable
    private fun ResponseObserver() {
        val responseState by viewModel.responseStateFlow.collectAsState()
        when (responseState) {
            is ResponseState.Error -> {
                Toast.makeText(
                    this,
                    (responseState as ResponseState.Error).message,
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.clearResponseState()
            }

            is ResponseState.Loading -> {
                LoadingIndicator()
            }

            else -> {}
        }
    }
}

@Composable
fun Screen(
    viewModel: MainViewModel
) {
    val sum = viewModel.sumFlow.collectAsState()
    val convertedSum = viewModel.conversedSumFlow.collectAsState()
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .fillMaxHeight(0.5f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Сумма для конвертации",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            OutlinedTextField(
                value = sum.value,
                onValueChange = {
                    viewModel.updateSum(it)
                },
                singleLine = true,
                placeholder = { Text(text = "Введите сумму") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Валюта",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CurrencyPicker(
                    selectedCurrency = viewModel.curFromFlow.collectAsState(),
                    onCurrencySelected = { viewModel.changeCurFrom(it) },
                )
                Icon(
                    painter = painterResource(id = R.drawable.round_compare_arrows_24),
                    contentDescription = "",
                    modifier = Modifier
                        .size(48.dp)
                        .clickable {
                            viewModel.swapCurrencies()
                        },
                    tint = MaterialTheme.colorScheme.onSurface
                )
                CurrencyPicker(
                    selectedCurrency = viewModel.curToFlow.collectAsState(),
                    onCurrencySelected = { viewModel.changeCurTo(it) },
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Сконвертированная сумма",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            OutlinedTextField(
                value = convertedSum.value,
                onValueChange = {},
                readOnly = true,
                singleLine = true,
                placeholder = { },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    viewModel.convert()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(text = "Конвертировать")
            }


        }
    }
}

@Composable
fun LoadingIndicator() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(60.dp),
            color = MaterialTheme.colorScheme.primary
        )
    }
}