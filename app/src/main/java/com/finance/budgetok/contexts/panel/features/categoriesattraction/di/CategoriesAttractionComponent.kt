package com.finance.budgetok.contexts.panel.features.categoriesattraction.di

import com.finance.budgetok.contexts.panel.features.categoriesattraction.CategoriesAttractionBottomSheetFragment
import com.finance.budgetok.infra.di.scopes.FeatureScope
import dagger.Component

@FeatureScope
@Component(
    modules = [
      CategoriesAttractionModule::class,
    ],
    dependencies = [
      CategoriesAttractionDependencies::class,
    ]
)
interface CategoriesAttractionComponent {
  fun inject(fragment: CategoriesAttractionBottomSheetFragment)

  companion object {
    val factory: Factory
      get() = DaggerCategoriesAttractionComponent.factory()
  }

  @Component.Factory
  interface Factory {
    fun create(
        dependencies: CategoriesAttractionDependencies,
    ): CategoriesAttractionComponent
  }
}