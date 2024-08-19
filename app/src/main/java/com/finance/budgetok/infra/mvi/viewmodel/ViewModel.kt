package com.finance.budgetok.infra.mvi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finance.budgetok.infra.mvi.ActionConsumer
import com.finance.budgetok.infra.mvi.EventsOwner
import com.finance.budgetok.infra.mvi.MVIAction
import com.finance.budgetok.infra.mvi.ActionConsumerOwner
import com.finance.budgetok.infra.mvi.MVIEvent
import com.finance.budgetok.infra.mvi.MVIFeature
import com.finance.budgetok.infra.mvi.MVIState
import com.finance.budgetok.infra.mvi.StateOwner
import com.finance.budgetok.infra.mvi.SavedState

abstract class ViewModel<State : MVIState, Action : MVIAction, Event : MVIEvent>(
  private val mviFeature: MVIFeature<State, Action, Event>,
  savedState: SavedState? = null,
) : ViewModel(),
    StateOwner.Provider<State>,
    EventsOwner.Provider<Event>,
  ActionConsumerOwner<Action> {

  override val actionConsumer: ActionConsumer<Action>
    get() = mviFeature

  override val stateOwner: StateOwner<State>
    get() = mviFeature

  override val eventsOwner: EventsOwner<Event>
    get() = mviFeature

  init {
    mviFeature.coroutineScope = viewModelScope
    mviFeature.initSavedState(savedState)
    mviFeature.startFeature()
  }
}
