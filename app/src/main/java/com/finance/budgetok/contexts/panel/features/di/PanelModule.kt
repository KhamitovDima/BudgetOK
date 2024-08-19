package com.finance.budgetok.contexts.panel.features.di

import androidx.lifecycle.ViewModelProvider.Factory
import com.finance.budgetok.contexts.panel.features.PanelViewModel
import com.finance.budgetok.contexts.panel.features.mvi.PanelFeature
import com.finance.budgetok.infra.ui.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
interface PanelModule {

    companion object {
        @Provides
        fun provideUnitsListViewModelFactory(
            viewModelFactory: ViewModelFactory,
            feature: PanelFeature,
        ): Factory {
            return viewModelFactory.create {
                PanelViewModel(
                    feature = feature
                )
            }
        }
    }
}