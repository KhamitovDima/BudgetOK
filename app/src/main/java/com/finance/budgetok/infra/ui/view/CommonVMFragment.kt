package com.finance.budgetok.infra.ui.view

import com.finance.budgetok.infra.di.componentdependencies.api.ComponentDependenciesRegistryProvider
import com.finance.budgetok.infra.di.componentdependencies.impl.delegatingComponentDependenciesRegistry
import com.finance.budgetok.infra.ui.view.lifecycle.CompositeLifecycleDelegate
import com.finance.budgetok.infra.ui.view.lifecycle.FragmentLifecycleDelegate
import com.finance.budgetok.infra.ui.view.lifecycle.LifecycleFragment
import com.finance.budgetok.infra.ui.view.popup.AlertDelegate
import com.finance.budgetok.infra.ui.view.popup.AlertView
import com.finance.budgetok.infra.ui.view.popup.ProgressDelegate
import com.finance.budgetok.infra.ui.view.popup.ProgressView

abstract class CommonVMFragment(contentLayoutId: Int) :
    LifecycleFragment(contentLayoutId),
    AlertView by AlertDelegate(),
    ProgressView by ProgressDelegate(),
  ComponentDependenciesRegistryProvider {

  override val componentDependenciesRegistry = delegatingComponentDependenciesRegistry()

  private val fragmentDelegates by lazy(mode = LazyThreadSafetyMode.NONE) {
    mutableListOf<FragmentLifecycleDelegate>()
  }

  fun addDelegate(vararg delegates: FragmentLifecycleDelegate) {
    fragmentDelegates.addAll(delegates)
  }

  open fun plusDelegate(): CompositeLifecycleDelegate = CompositeLifecycleDelegate()

  final override fun onBuildDelegate(): FragmentLifecycleDelegate =
    CompositeLifecycleDelegate(
        delegates = fragmentDelegates
    ) + plusDelegate()
}