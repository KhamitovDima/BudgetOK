package com.finance.budgetok.infra.navigation.di

import com.finance.budgetok.infra.navigation.JetpackNavigator
import com.finance.budgetok.infra.navigation.Navigator
import dagger.Binds
import dagger.Module

@Module
interface NavigationModule {
  @Binds
  fun bindsNavigator(
    jetpackNavigator: JetpackNavigator,
  ): Navigator
}