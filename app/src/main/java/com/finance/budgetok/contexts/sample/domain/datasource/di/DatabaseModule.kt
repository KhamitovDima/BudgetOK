package com.finance.budgetok.contexts.sample.domain.datasource.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.finance.budgetok.contexts.sample.domain.datasource.dao.AccountDao
import com.finance.budgetok.contexts.sample.domain.datasource.dao.BudgetDao
import com.finance.budgetok.contexts.sample.domain.datasource.dao.DolgUserDao
import com.finance.budgetok.contexts.sample.domain.datasource.dao.OperationDao
import com.finance.budgetok.contexts.sample.domain.datasource.dao.SettingsDao
import com.finance.budgetok.contexts.sample.domain.datasource.dao.SubcategoryDao
import com.finance.budgetok.contexts.sample.domain.datasource.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Application): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, "finance-db"
        ).build()
    }

    @Provides
    fun provideAccountDao(database: AppDatabase): AccountDao {
        return database.accountDao()
    }

    @Provides
    fun provideOperationDao(database: AppDatabase): OperationDao {
        return database.operationDao()
    }

    @Provides
    fun provideSubcategoryDao(database: AppDatabase): SubcategoryDao {
        return database.subcategoryDao()
    }

    @Provides
    fun provideDolgUserDao(database: AppDatabase): DolgUserDao {
        return database.dolgUserDao()
    }

    @Provides
    fun provideBudgetDao(database: AppDatabase): BudgetDao {
        return database.budgetDao()
    }

    @Provides
    fun provideSettingDao(database: AppDatabase): SettingsDao {
        return database.settingDao()
    }
}