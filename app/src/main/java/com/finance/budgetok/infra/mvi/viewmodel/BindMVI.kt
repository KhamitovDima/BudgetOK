package com.finance.budgetok.infra.mvi.viewmodel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import com.finance.budgetok.infra.mvi.EventsOwner
import com.finance.budgetok.infra.mvi.EventsReceiver
import com.finance.budgetok.infra.mvi.MVIEvent
import com.finance.budgetok.infra.mvi.MVIState
import com.finance.budgetok.infra.mvi.StateOwner
import com.finance.budgetok.infra.mvi.StateRenderer

fun <State : MVIState, Event : MVIEvent, VM> LifecycleOwner.bindMVI(
  stateRenderer: StateRenderer<State>? = null,
  eventsReceiver: EventsReceiver<Event>? = null,
  viewModel: VM,
) where VM : ViewModel, VM : StateOwner.Provider<State>, VM : EventsOwner.Provider<Event>  {
  stateRenderer?.apply {
    val stateOwner = viewModel.stateOwner
    lifecycleScope.launch {
      repeatOnLifecycle(Lifecycle.State.STARTED) {
        stateOwner.state.collect { state -> render(state) }
      }
    }
  }

  eventsReceiver?.apply {
    val eventsOwner = viewModel.eventsOwner
    lifecycleScope.launch {
      repeatOnLifecycle(Lifecycle.State.STARTED) {
        eventsOwner.events.collect { event -> onEvent(event) }
      }
    }
  }
}
