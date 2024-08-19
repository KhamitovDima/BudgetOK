package com.finance.budgetok.app.dependencies

import com.finance.budgetok.contexts.panel.features.di.PanelDependencies
import com.finance.budgetok.contexts.sample.features.di.SampleDependencies
import com.finance.budgetok.infra.di.componentdependencies.api.ComponentDependencies
import com.finance.budgetok.infra.di.scopes.dependencies.AppScopeDependencies


interface AllAppScopeDependencies :
    AppScopeDependencies,
    SampleDependencies,
    PanelDependencies,
    ComponentDependencies
