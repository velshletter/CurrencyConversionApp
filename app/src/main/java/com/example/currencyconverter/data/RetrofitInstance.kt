package com.example.currencyconverter.data



import com.example.currencyconverter.AppConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit = Retrofit.Builder()
        .baseUrl(AppConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val currencyApi: CurrencyApi = retrofit.create(CurrencyApi::class.java)
}