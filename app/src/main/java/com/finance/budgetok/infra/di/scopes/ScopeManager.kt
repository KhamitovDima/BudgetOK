package com.finance.budgetok.infra.di.scopes

import com.finance.budgetok.app.component.AppScopeComponent
import com.finance.budgetok.infra.di.scopes.dependencies.AppComponent


interface ScopeManagerOwner {
  val scopeManager: ScopeManager
}

interface ScopeManager {
  val appComponent: AppComponent

  /*fun createUnitScope(unitContext: UnitContext)
  fun restoreScopes(bundle: Bundle?)
  fun saveScopes(bundle: Bundle)*/
}

class ScopeManagerImpl(
  private val appContext: AppContext,
) : ScopeManager {

  override val appComponent: AppScopeComponent = AppScopeComponent.builder
    .context(appContext.application)
    .build()
}
