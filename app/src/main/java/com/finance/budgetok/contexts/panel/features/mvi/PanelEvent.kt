package com.finance.budgetok.contexts.panel.features.mvi

import com.finance.budgetok.infra.mvi.MVIEvent

sealed interface PanelEvent : MVIEvent {

    data object event : PanelEvent
}