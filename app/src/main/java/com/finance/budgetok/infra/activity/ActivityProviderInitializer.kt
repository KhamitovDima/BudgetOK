package com.finance.budgetok.infra.activity

import android.app.Application
import android.content.Context
import androidx.startup.Initializer

class ActivityProviderInitializer : Initializer<Unit> {
  override fun create(context: Context) {
    AppActivityProvider.initialize(context as Application)
  }

  override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}