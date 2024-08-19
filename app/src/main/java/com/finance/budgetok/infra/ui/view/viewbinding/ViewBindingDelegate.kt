package com.finance.budgetok.infra.ui.view.viewbinding

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.finance.budgetok.infra.ui.view.CommonVMBottomSheetFragment
import com.finance.budgetok.infra.ui.view.CommonVMFragment
import com.finance.budgetok.infra.ui.view.lifecycle.FragmentLifecycleDelegate
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified T : ViewBinding> CommonVMFragment.viewBinding() =
  FragmentViewBindingDelegate(T::class.java, this)

inline fun <reified T : ViewBinding> CommonVMBottomSheetFragment.viewBinding() =
  FragmentViewBindingDelegate(T::class.java, this)

class FragmentViewBindingDelegate<T : ViewBinding>(
  bindingClass: Class<T>,
  fragment: Fragment,
) : ReadOnlyProperty<Fragment, T>, FragmentLifecycleDelegate {

  /**
   * initiate variable for binding view
   */
  private var binding: T? = null

  /**
   * get the bind method from View class
   */
  private val bindMethod = bindingClass.getMethod("bind", View::class.java)

  init {
    (fragment as? CommonVMFragment)?.addDelegate(this)
    (fragment as? CommonVMBottomSheetFragment)?.addDelegate(this)
  }

  override fun onDestroyView(
    fragment: Fragment,
    view: View,
  ) {
    binding = null
  }

  @Suppress("UNCHECKED_CAST")
  override fun getValue(
    thisRef: Fragment,
    property: KProperty<*>,
  ): T {
    binding?.let { return it }

    /**
     * Bind layout
     */
    val invoke = bindMethod.invoke(null, thisRef.requireView()) as T

    return invoke.also { this.binding = it }
  }
}