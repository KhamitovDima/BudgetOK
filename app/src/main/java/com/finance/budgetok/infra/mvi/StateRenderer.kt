package com.finance.budgetok.infra.mvi

interface StateRenderer<State : MVIState> {
  fun render(state: State) {}
}
