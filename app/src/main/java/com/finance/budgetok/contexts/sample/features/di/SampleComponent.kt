package com.finance.budgetok.contexts.sample.features.di

import com.finance.budgetok.contexts.panel.features.di.PanelDependencies
import com.finance.budgetok.contexts.panel.features.di.PanelModule
import com.finance.budgetok.contexts.panel.features.PanelFragment
import com.finance.budgetok.contexts.sample.features.SampleFragment
import com.finance.budgetok.infra.di.scopes.FeatureScope
import dagger.Component

@FeatureScope
@Component(
    modules = [
        SampleModule::class,
    ],
    dependencies = [
        SampleDependencies::class,
    ]
)
interface SampleComponent {
    fun inject(fragment: SampleFragment)

    companion object {
        val factory: Factory
            get() = DaggerSampleComponent.factory()
    }

    @Component.Factory
    interface Factory {
        fun create(
            dependencies: SampleDependencies,
        ): SampleComponent
    }
}