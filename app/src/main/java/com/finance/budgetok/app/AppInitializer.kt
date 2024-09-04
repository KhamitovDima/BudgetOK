package com.finance.budgetok.app

import android.content.Context
import androidx.startup.Initializer
import com.finance.budgetok.infra.activity.ActivityProviderInitializer

class AppInitializer : Initializer<Unit> {
  override fun create(context: Context) {
    // Nothing to do here. Just initializing dependencies
  }

  override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf(
      ActivityProviderInitializer::class.java,
  )
}