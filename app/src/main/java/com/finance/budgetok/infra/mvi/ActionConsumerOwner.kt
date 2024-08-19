package com.finance.budgetok.infra.mvi

interface ActionConsumerOwner<Action : MVIAction> {
  val actionConsumer: ActionConsumer<Action>
}