package com.finance.budgetok.contexts.panel.features.di

import com.finance.budgetok.contexts.panel.domain.usecase.PanelUseCase

interface PanelDependencies {
    val panelUseCase: PanelUseCase
}