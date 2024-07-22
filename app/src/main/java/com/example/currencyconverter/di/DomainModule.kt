package com.example.currencyconverter.di

import com.example.currencyconverter.domain.interfaces.CurrencyRepository
import com.example.currencyconverter.domain.usecase.ConvertUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    @ViewModelScoped
    fun provideConvertUseCase(currencyRepository: CurrencyRepository): ConvertUseCase {
        return ConvertUseCase(currencyRepository)
    }
}