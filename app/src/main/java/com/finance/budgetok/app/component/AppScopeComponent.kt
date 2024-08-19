package com.finance.budgetok.app.component

import android.app.Application
import com.finance.budgetok.app.BudgetOkApp
import com.finance.budgetok.app.dependencies.AllAppScopeDependencies
import com.finance.budgetok.app.modules.AppScopeModule
import com.finance.budgetok.contexts.sample.domain.datasource.di.DatabaseModule
import com.finance.budgetok.infra.di.scopes.dependencies.AppComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
      AppScopeModule::class,
      DatabaseModule::class,
    ],
)
interface AppScopeComponent : AppComponent, AllAppScopeDependencies {
  fun inject(app: BudgetOkApp)

  companion object {
    val builder: Builder
      get() = DaggerAppScopeComponent.builder()
  }

  @Component.Builder
  interface Builder {
    fun context(@BindsInstance application: Application): Builder
    fun build(): AppScopeComponent
  }
}