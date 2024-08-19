package com.finance.budgetok.infra.ui.view.lifecycle

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.finance.budgetok.infra.ui.view.lifecycle.FragmentLifecycleDelegate

abstract class LifecycleFragment(contentLayoutId: Int) :
    Fragment(contentLayoutId)
{
  private lateinit var delegate: FragmentLifecycleDelegate

  abstract fun onBuildDelegate(): FragmentLifecycleDelegate

  protected open fun onViewCreate() {}

  final override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?,
  ) {
    super.onViewCreated(view, savedInstanceState)
    onViewCreate()
    delegate.onCreateView(this, view)
  }

  override fun onDestroyView() {
    delegate.onDestroyView(this, requireView())
    super.onDestroyView()
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    delegate = onBuildDelegate()
    delegate.onCreate(this)
  }

  override fun onResume() {
    super.onResume()
    delegate.onResume(this)
  }

  override fun onPause() {
    super.onPause()
    delegate.onPause(this)
  }

  override fun onDestroy() {
    delegate.onDestroy(this)
    super.onDestroy()
  }

  override fun onStart() {
    delegate.onStart(this)
    super.onStart()
  }

  override fun onStop() {
    delegate.onStop(this)
    super.onStop()
  }
}