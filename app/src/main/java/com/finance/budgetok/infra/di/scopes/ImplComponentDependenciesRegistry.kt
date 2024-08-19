package com.finance.budgetok.infra.di.scopes

import com.finance.budgetok.infra.di.componentdependencies.api.ComponentDependencies
import com.finance.budgetok.infra.di.componentdependencies.api.ComponentDependenciesRegistry
import kotlin.reflect.KClass
import kotlin.reflect.cast

class ImplComponentDependenciesRegistry(
  private val scopeManager: ScopeManager,
) : ComponentDependenciesRegistry {

  override fun <T : ComponentDependencies> findDependencies(
    clazz: KClass<out T>,
  ): T {
    return when {
      isAppScope(clazz) -> {
        clazz.cast((scopeManager.appComponent))
      }
      else -> {
        throw DependenciesNotFoundException(
            "There is no active " +
            "component dependencies implementing $clazz"
        )
      }
    }
  }

  private fun <T : ComponentDependencies> isAppScope(clazz: KClass<out T>) =
    clazz.java.isAssignableFrom(scopeManager.appComponent.javaClass)


  class DependenciesNotFoundException(message: String) : Exception(message)
}
