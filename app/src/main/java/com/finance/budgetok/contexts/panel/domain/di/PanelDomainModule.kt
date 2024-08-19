package com.finance.budgetok.contexts.panel.domain.di

import com.finance.budgetok.contexts.panel.domain.usecase.PanelUseCase
import com.finance.budgetok.contexts.panel.domain.usecase.PanelUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface PanelDomainModule {
    @Binds
    fun bindsPanelUseCase(
        getValueUseCaseImpl: PanelUseCaseImpl,
    ): PanelUseCase
}