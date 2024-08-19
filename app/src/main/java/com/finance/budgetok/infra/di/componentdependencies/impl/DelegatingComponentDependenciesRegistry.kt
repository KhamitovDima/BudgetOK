package com.finance.budgetok.infra.di.componentdependencies.impl

import android.content.Context
import android.content.ContextWrapper
import android.view.View
import androidx.fragment.app.Fragment
import com.finance.budgetok.infra.di.componentdependencies.api.ComponentDependencies
import com.finance.budgetok.infra.di.componentdependencies.api.ComponentDependenciesRegistry
import com.finance.budgetok.infra.di.componentdependencies.api.ComponentDependenciesRegistryProvider
import kotlin.reflect.KClass

fun Context.delegatingComponentDependenciesRegistry(): ComponentDependenciesRegistry =
  DelegatingComponentDependenciesRegistry {
    applicationContext as ComponentDependenciesRegistryProvider
  }

fun Fragment.delegatingComponentDependenciesRegistry(): ComponentDependenciesRegistry =
  DelegatingComponentDependenciesRegistry {
    val delegate = requireActivity()
    delegate as ComponentDependenciesRegistryProvider
  }

fun View.delegatingComponentDependenciesRegistry(): ComponentDependenciesRegistry =
  DelegatingComponentDependenciesRegistry {
    findProvider(context)
  }

private tailrec fun findProvider(context: Context): ComponentDependenciesRegistryProvider =
  (context as? ComponentDependenciesRegistryProvider)
      ?: findProvider((context as ContextWrapper).baseContext)

private class DelegatingComponentDependenciesRegistry(
  private val provideDelegate: () -> ComponentDependenciesRegistryProvider,
) : ComponentDependenciesRegistry {
  override fun <T : ComponentDependencies> findDependencies(
    clazz: KClass<out T>,
  ): T {
    return provideDelegate()
        .componentDependenciesRegistry
        .findDependencies(clazz)
  }
}
