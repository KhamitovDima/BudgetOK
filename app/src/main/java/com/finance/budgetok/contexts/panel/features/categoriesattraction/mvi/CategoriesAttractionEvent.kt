package com.finance.budgetok.contexts.panel.features.categoriesattraction.mvi

import com.finance.budgetok.infra.mvi.MVIEvent


sealed interface CategoriesAttractionEvent : MVIEvent {
  data object exampleEvent : CategoriesAttractionEvent
}