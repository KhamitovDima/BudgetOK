package com.finance.budgetok.contexts.sample.features.di

import androidx.lifecycle.ViewModelProvider.Factory
import com.finance.budgetok.contexts.panel.features.PanelViewModel
import com.finance.budgetok.contexts.panel.features.mvi.PanelFeature
import com.finance.budgetok.contexts.sample.features.SampleViewModel
import com.finance.budgetok.contexts.sample.features.mvi.SampleFeature
import com.finance.budgetok.infra.ui.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
interface SampleModule {

    companion object {
        @Provides
        fun provideUnitsListViewModelFactory(
            viewModelFactory: ViewModelFactory,
            feature: SampleFeature,
        ): Factory {
            return viewModelFactory.create {
                SampleViewModel(
                    feature = feature
                )
            }
        }
    }
}