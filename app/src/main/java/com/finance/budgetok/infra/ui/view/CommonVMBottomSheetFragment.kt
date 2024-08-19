package com.finance.budgetok.infra.ui.view

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.finance.budgetok.infra.di.componentdependencies.api.ComponentDependenciesRegistryProvider
import com.finance.budgetok.infra.di.componentdependencies.impl.delegatingComponentDependenciesRegistry
import com.finance.budgetok.infra.ui.view.lifecycle.CompositeLifecycleDelegate
import com.finance.budgetok.infra.ui.view.lifecycle.FragmentLifecycleDelegate
import com.finance.budgetok.infra.ui.view.lifecycle.LifecycleBottomSheetFragment
import com.finance.budgetok.infra.ui.view.popup.AlertDelegate
import com.finance.budgetok.infra.ui.view.popup.AlertView

abstract class CommonVMBottomSheetFragment :
    LifecycleBottomSheetFragment(),
    AlertView by AlertDelegate(),
  ComponentDependenciesRegistryProvider {

  override val componentDependenciesRegistry = delegatingComponentDependenciesRegistry()

  private val fragmentDelegates by lazy(mode = LazyThreadSafetyMode.NONE) {
    mutableListOf<FragmentLifecycleDelegate>()
  }

  fun addDelegate(delegate: FragmentLifecycleDelegate) {
    fragmentDelegates.add(delegate)
  }

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    return FitsSystemWindowsBottomSheetDialog(requireContext(), theme)
  }

  override fun onBuildDelegate(): FragmentLifecycleDelegate =
    CompositeLifecycleDelegate(
        delegates = fragmentDelegates
    )

  protected abstract fun getLayoutId(): Int

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View? {
    return inflater.inflate(getLayoutId(), container, false)
  }
}