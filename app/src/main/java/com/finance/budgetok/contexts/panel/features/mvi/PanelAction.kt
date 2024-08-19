package com.finance.budgetok.contexts.panel.features.mvi

import com.finance.budgetok.infra.mvi.MVIAction

sealed interface PanelAction : MVIAction {

    data object ButtonClicked : PanelAction
    data class SomeValueLoaded(val value: Int) : PanelAction
}