package com.finance.budgetok.infra.ui.view.lifecycle

import android.view.View
import androidx.fragment.app.Fragment

interface FragmentLifecycleDelegate {

  fun onCreate(fragment: Fragment) {}

  fun onDestroy(fragment: Fragment) {}

  fun onResume(fragment: Fragment) {}

  fun onPause(fragment: Fragment) {}

  fun onStart(fragment: Fragment) {}

  fun onStop(fragment: Fragment) {}

  fun onCreateView(
    fragment: Fragment,
    view: View,
  ) {
  }

  fun onDestroyView(
    fragment: Fragment,
    view: View,
  ) {
  }
}

class CompositeLifecycleDelegate(
  private val delegates: List<FragmentLifecycleDelegate> = emptyList(),
) : FragmentLifecycleDelegate {

  constructor(delegate: FragmentLifecycleDelegate) : this(listOf(delegate))

  override fun onCreate(fragment: Fragment) {
    delegates.forEach { delegate -> delegate.onCreate(fragment) }
  }

  override fun onDestroy(fragment: Fragment) {
    delegates.forEach { delegate -> delegate.onDestroy(fragment) }
  }

  override fun onCreateView(
    fragment: Fragment,
    view: View,
  ) {
    delegates.forEach { delegate -> delegate.onCreateView(fragment, view) }
  }

  override fun onDestroyView(
    fragment: Fragment,
    view: View,
  ) {
    delegates.forEach { delegate -> delegate.onDestroyView(fragment, view) }
  }

  override fun onResume(fragment: Fragment) {
    delegates.forEach { delegate -> delegate.onResume(fragment) }
  }

  override fun onPause(fragment: Fragment) {
    delegates.forEach { delegate -> delegate.onPause(fragment) }
  }

  override fun onStart(fragment: Fragment) {
    delegates.forEach { delegate -> delegate.onStart(fragment) }
  }

  override fun onStop(fragment: Fragment) {
    delegates.forEach { delegate -> delegate.onStop(fragment) }
  }

  operator fun plus(delegate: CompositeLifecycleDelegate): CompositeLifecycleDelegate {
    return CompositeLifecycleDelegate(
        delegates + delegate.delegates
    )
  }
}
