package com.finance.budgetok.contexts.panel.features.categoriesattraction

import com.finance.budgetok.contexts.panel.features.categoriesattraction.mvi.CategoriesAttractionAction
import com.finance.budgetok.contexts.panel.features.categoriesattraction.mvi.CategoriesAttractionEvent
import com.finance.budgetok.contexts.panel.features.categoriesattraction.mvi.CategoriesAttractionFeature
import com.finance.budgetok.contexts.panel.features.categoriesattraction.mvi.CategoriesAttractionState
import com.finance.budgetok.infra.mvi.viewmodel.ViewModel

class CategoriesAttractionViewModel(
  feature: CategoriesAttractionFeature,
) : ViewModel<CategoriesAttractionState, CategoriesAttractionAction, CategoriesAttractionEvent>(feature)