package com.finance.budgetok.contexts.sample.domain.di

import com.finance.budgetok.contexts.sample.domain.usecase.GetValueUseCase
import com.finance.budgetok.contexts.sample.domain.usecase.GetValueUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface SampleDomainModule {
    @Binds
    fun bindsGetValueUseCase(
        getValueUseCaseImpl: GetValueUseCaseImpl,
    ): GetValueUseCase
}