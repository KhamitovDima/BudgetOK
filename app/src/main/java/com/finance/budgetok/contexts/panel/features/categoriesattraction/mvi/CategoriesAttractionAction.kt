package com.finance.budgetok.contexts.panel.features.categoriesattraction.mvi

import com.finance.budgetok.infra.mvi.MVIAction


sealed interface CategoriesAttractionAction : MVIAction {
  data object ExampleAction : CategoriesAttractionAction
}