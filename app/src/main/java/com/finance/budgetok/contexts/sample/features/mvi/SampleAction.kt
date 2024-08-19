package com.finance.budgetok.contexts.sample.features.mvi

import com.finance.budgetok.contexts.panel.features.mvi.PanelAction
import com.finance.budgetok.infra.mvi.MVIAction

sealed interface SampleAction : MVIAction {

    data object ButtonClicked : SampleAction
    data class SomeValueLoaded(val value: Int) : SampleAction
}