package com.finance.budgetok.infra.mvi

import kotlinx.coroutines.flow.SharedFlow

interface EventsOwner<Event : MVIEvent> {
  val events: SharedFlow<Event>

  interface Provider<Event : MVIEvent> {
    val eventsOwner: EventsOwner<Event>
  }
}