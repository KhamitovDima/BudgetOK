package com.finance.budgetok.infra.mvi

interface EventsReceiver<Event : MVIEvent> {
  fun onEvent(event: Event) {}
}
