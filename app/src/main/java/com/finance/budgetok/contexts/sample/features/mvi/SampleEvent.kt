package com.finance.budgetok.contexts.sample.features.mvi

import com.finance.budgetok.contexts.panel.features.mvi.PanelEvent
import com.finance.budgetok.infra.mvi.MVIEvent

sealed interface SampleEvent : MVIEvent {

    data object event : SampleEvent
}