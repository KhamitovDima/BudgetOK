package com.finance.budgetok.contexts.sample.domain.datasource.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.finance.budgetok.contexts.sample.domain.datasource.dao.AccountDao
import com.finance.budgetok.contexts.sample.domain.datasource.dao.BudgetDao
import com.finance.budgetok.contexts.sample.domain.datasource.dao.DolgUserDao
import com.finance.budgetok.contexts.sample.domain.datasource.dao.OperationDao
import com.finance.budgetok.contexts.sample.domain.datasource.dao.SettingsDao
import com.finance.budgetok.contexts.sample.domain.datasource.dao.SubcategoryDao
import com.finance.budgetok.contexts.sample.domain.datasource.entities.Account
import com.finance.budgetok.contexts.sample.domain.datasource.entities.Budget
import com.finance.budgetok.contexts.sample.domain.datasource.entities.DolgUser
import com.finance.budgetok.contexts.sample.domain.datasource.entities.Operation
import com.finance.budgetok.contexts.sample.domain.datasource.entities.Settings
import com.finance.budgetok.contexts.sample.domain.datasource.entities.Subcategory

@Database(
    entities = [
        Account::class,
        Operation::class,
        Subcategory::class,
        DolgUser::class,
        Budget::class,
        Settings::class
    ], version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun operationDao(): OperationDao
    abstract fun subcategoryDao(): SubcategoryDao
    abstract fun dolgUserDao(): DolgUserDao
    abstract fun budgetDao(): BudgetDao
    abstract fun settingDao(): SettingsDao
}