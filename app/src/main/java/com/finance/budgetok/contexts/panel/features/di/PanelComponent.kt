package com.finance.budgetok.contexts.panel.features.di

import com.finance.budgetok.contexts.panel.features.PanelFragment
import com.finance.budgetok.infra.di.scopes.FeatureScope
import dagger.Component

@FeatureScope
@Component(
    modules = [
        PanelModule::class,
    ],
    dependencies = [
        PanelDependencies::class,
    ]
)
interface PanelComponent {
    fun inject(fragment: PanelFragment)

    companion object {
        val factory: Factory
            get() = DaggerPanelComponent.factory()
    }

    @Component.Factory
    interface Factory {
        fun create(
            dependencies: PanelDependencies,
        ): PanelComponent
    }
}