package com.finance.budgetok.infra.mvi

import kotlinx.coroutines.flow.StateFlow

interface StateOwner<State : MVIState> {
  val state: StateFlow<State>

  interface Provider<State : MVIState> {
    val stateOwner: StateOwner<State>
  }
}
