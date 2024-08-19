package com.finance.budgetok.infra.mvi

interface EventConsumer<Event : MVIEvent> {
  fun consumeEvent(event: Event)
}