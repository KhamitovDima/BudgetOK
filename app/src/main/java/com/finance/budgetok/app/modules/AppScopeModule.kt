package com.finance.budgetok.app.modules

import android.app.Application
import android.content.Context
import android.content.res.AssetManager
import com.finance.budgetok.contexts.panel.domain.di.PanelDomainModule
import com.finance.budgetok.contexts.sample.domain.di.SampleDomainModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        SampleDomainModule::class,
        PanelDomainModule::class
    ],
)
class AppScopeModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideAssetManager(context: Context): AssetManager = context.assets
}