package com.example.currencyconverter.di

import com.example.currencyconverter.data.CurrencyRepositoryImpl
import com.example.currencyconverter.data.RetrofitInstance
import com.example.currencyconverter.domain.interfaces.CurrencyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideCurrencyRepository() : CurrencyRepository {
        return CurrencyRepositoryImpl(RetrofitInstance.currencyApi)
    }

}