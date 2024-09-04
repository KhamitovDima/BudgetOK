package com.finance.budgetok.contexts.panel.features.categoriesattraction.di

import androidx.lifecycle.ViewModelProvider.Factory
import com.finance.budgetok.contexts.panel.features.categoriesattraction.CategoriesAttractionViewModel
import com.finance.budgetok.contexts.panel.features.categoriesattraction.mvi.CategoriesAttractionFeature
import com.finance.budgetok.infra.ui.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
interface CategoriesAttractionModule {

  companion object {
    @Provides
    fun provideCategoriesAttractionViewModelFactory(
      feature: CategoriesAttractionFeature,
      viewModelFactory: ViewModelFactory,
    ): Factory {
      return viewModelFactory.create {
        CategoriesAttractionViewModel(
            feature = feature
        )
      }
    }
  }
}