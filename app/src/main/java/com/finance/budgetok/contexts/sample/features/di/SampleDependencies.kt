package com.finance.budgetok.contexts.sample.features.di

import com.finance.budgetok.contexts.sample.domain.usecase.GetValueUseCase

interface SampleDependencies {
    val getValueUseCase: GetValueUseCase
}