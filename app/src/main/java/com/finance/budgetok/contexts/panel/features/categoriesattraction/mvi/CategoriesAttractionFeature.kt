package com.finance.budgetok.contexts.panel.features.categoriesattraction.mvi

import com.finance.budgetok.infra.mvi.MVIFeature
import javax.inject.Inject


private const val CLIPBOARD_LABEL = "App Info"

class CategoriesAttractionFeature @Inject constructor() :
    MVIFeature<CategoriesAttractionState, CategoriesAttractionAction, CategoriesAttractionEvent>() {

    override fun initialState(): CategoriesAttractionState = CategoriesAttractionState()

    override suspend fun processAction(
        state: CategoriesAttractionState,
        action: CategoriesAttractionAction,
    ) {
        when (action) {
            is CategoriesAttractionAction.ExampleAction -> {}
            else -> Unit
        }
    }

    override fun reduce(
        state: CategoriesAttractionState,
        action: CategoriesAttractionAction,
    ): CategoriesAttractionState {
        return when (action) {
            CategoriesAttractionAction.ExampleAction -> state
            else -> state
        }
    }
}