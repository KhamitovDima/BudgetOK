@file:Suppress("SpellCheckingInspection", "unused")

package com.finance.budgetok.app

import android.app.Application
import com.finance.budgetok.app.component.AppScopeComponent
import com.finance.budgetok.infra.di.componentdependencies.api.ComponentDependenciesRegistryProvider
import com.finance.budgetok.infra.di.scopes.AppContext
import com.finance.budgetok.infra.di.scopes.ScopeManager
import com.finance.budgetok.infra.di.scopes.ScopeManagerOwner
import kotlin.LazyThreadSafetyMode.NONE
import com.finance.budgetok.infra.di.scopes.ImplComponentDependenciesRegistry
import com.finance.budgetok.infra.di.scopes.ScopeManagerImpl

class BudgetOkApp :
    Application(),
    ScopeManagerOwner,
    ComponentDependenciesRegistryProvider {

    override val scopeManager: ScopeManager by lazy(NONE) {
        ScopeManagerImpl(AppContext(this))
    }

    override val componentDependenciesRegistry = ImplComponentDependenciesRegistry(
        scopeManager = scopeManager
    )

    override fun onCreate() {
        super.onCreate()
        (scopeManager.appComponent as AppScopeComponent).inject(this)
    }
}
