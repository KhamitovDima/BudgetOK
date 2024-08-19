package com.finance.budgetok.contexts.panel.features.mvi

import com.finance.budgetok.contexts.panel.domain.usecase.PanelUseCase
import com.finance.budgetok.contexts.sample.domain.usecase.GetValueUseCase
import com.finance.budgetok.infra.mvi.MVIFeature
import javax.inject.Inject


class PanelFeature @Inject constructor(
    private val panelUseCase: PanelUseCase
) : MVIFeature<PanelState, PanelAction, PanelEvent>() {

    override fun initialState() = PanelState()

    override fun reduce(state: PanelState, action: PanelAction): PanelState {
        return when(action) {
            is PanelAction.SomeValueLoaded -> state.copy(
                text = action.value
            )
            else -> state
        }
    }

    override suspend fun processAction(state: PanelState, action: PanelAction) {
        when (action) {
            PanelAction.ButtonClicked -> getValue(state.text)
            else -> Unit
        }
    }

    private suspend fun getValue(currentValue: Int) {
        val value = panelUseCase(currentValue)
        consumeAction(PanelAction.SomeValueLoaded(value))
    }


}