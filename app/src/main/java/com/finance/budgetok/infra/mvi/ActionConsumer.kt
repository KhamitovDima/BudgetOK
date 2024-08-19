package com.finance.budgetok.infra.mvi

interface ActionConsumer<Action : MVIAction> {
  fun consumeAction(action: Action)
}