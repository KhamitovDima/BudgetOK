package com.finance.budgetok.contexts.sample.features


import com.finance.budgetok.contexts.sample.features.mvi.SampleAction
import com.finance.budgetok.contexts.sample.features.mvi.SampleEvent
import com.finance.budgetok.contexts.sample.features.mvi.SampleFeature
import com.finance.budgetok.contexts.sample.features.mvi.SampleState
import com.finance.budgetok.infra.mvi.viewmodel.ViewModel

class SampleViewModel(
  feature: SampleFeature,
) : ViewModel<SampleState, SampleAction, SampleEvent>(feature)