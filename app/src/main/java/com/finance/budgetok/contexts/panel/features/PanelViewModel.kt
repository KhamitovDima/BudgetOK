package com.finance.budgetok.contexts.panel.features

import com.finance.budgetok.contexts.panel.features.mvi.PanelAction
import com.finance.budgetok.contexts.panel.features.mvi.PanelEvent
import com.finance.budgetok.contexts.panel.features.mvi.PanelFeature
import com.finance.budgetok.contexts.panel.features.mvi.PanelState
import com.finance.budgetok.infra.mvi.viewmodel.ViewModel

class PanelViewModel(
  feature: PanelFeature,
) : ViewModel<PanelState, PanelAction, PanelEvent>(feature)