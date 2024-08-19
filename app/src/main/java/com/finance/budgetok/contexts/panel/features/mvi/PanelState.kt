package com.finance.budgetok.contexts.panel.features.mvi

import androidx.compose.runtime.Immutable
import com.finance.budgetok.infra.mvi.MVIState

@Immutable
data class PanelState(
    val title:String = "Click Me",
    val text: Int = 0,
) : MVIState