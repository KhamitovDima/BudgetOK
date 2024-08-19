package com.finance.budgetok.infra.ui.viewmodel

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import javax.inject.Inject

class ViewModelFactory @Inject constructor() {

  @Suppress("UNCHECKED_CAST")
  inline fun create(crossinline creator: () -> ViewModel): Factory {
    return object : Factory {
      override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return creator() as T
      }
    }
  }

  @Suppress("UNCHECKED_CAST")
  inline fun createWithSavedState(
    crossinline creator: (savedState: SavedStateHandle) -> ViewModel,
  ): Factory {
    return object : AbstractSavedStateViewModelFactory() {
      override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle,
      ): T {
        return creator(handle) as T
      }
    }
  }
}