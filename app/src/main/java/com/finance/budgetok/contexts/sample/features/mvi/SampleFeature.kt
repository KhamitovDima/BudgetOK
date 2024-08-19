package com.finance.budgetok.contexts.sample.features.mvi

import com.finance.budgetok.contexts.sample.domain.usecase.GetValueUseCase
import com.finance.budgetok.infra.mvi.MVIFeature
import javax.inject.Inject


class SampleFeature @Inject constructor(
    private val getValueuseCase: GetValueUseCase
) : MVIFeature<SampleState, SampleAction, SampleEvent>() {

    override fun initialState() = SampleState()

    override fun reduce(state: SampleState, action: SampleAction): SampleState {
        return when(action) {
            is SampleAction.SomeValueLoaded -> state.copy(
                text = action.value
            )
            else -> state
        }
    }

    override suspend fun processAction(state: SampleState, action: SampleAction) {
        when (action) {
            SampleAction.ButtonClicked -> getValue(state.text)
            else -> Unit
        }
    }

    private suspend fun getValue(currentValue: Int) {
        val value = getValueuseCase(currentValue)
        consumeAction(SampleAction.SomeValueLoaded(value))
    }


}