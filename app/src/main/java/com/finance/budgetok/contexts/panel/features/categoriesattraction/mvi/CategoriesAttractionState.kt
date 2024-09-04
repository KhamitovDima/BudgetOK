package com.finance.budgetok.contexts.panel.features.categoriesattraction.mvi

import androidx.compose.runtime.Immutable
import com.finance.budgetok.infra.mvi.MVIState

@Immutable
data class CategoriesAttractionState(
    val example: String = "String"
) : MVIState