package com.finance.budgetok.infra.di.componentdependencies.api

import kotlin.reflect.KClass

interface ComponentDependencies

interface ComponentDependenciesRegistryProvider {
  val componentDependenciesRegistry: ComponentDependenciesRegistry
}

interface ComponentDependenciesRegistry {
  fun <T : ComponentDependencies> findDependencies(
    clazz: KClass<out T>,
  ): T
}

inline fun <reified T : ComponentDependencies>
        ComponentDependenciesRegistryProvider.findDependencies(): T {
  return componentDependenciesRegistry.findDependencies(T::class)
}

inline fun <reified T : ComponentDependencies>
        ComponentDependenciesRegistry.findDependencies(): T {
  return findDependencies(T::class)
}