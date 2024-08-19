package com.finance.budgetok.contexts.sample.domain.datasource.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.finance.budgetok.contexts.sample.domain.datasource.entities.Account
import com.finance.budgetok.contexts.sample.domain.datasource.entities.Budget

@Dao
interface BudgetDao {
    @Query("SELECT * FROM budgets WHERE isDeleted = 0")
    fun getAllBudgets(): List<Budget>

    @Query("SELECT * FROM budgets WHERE id = :id AND isDeleted = 0")
    fun getBudgetById(id: String): Budget?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBudget(budget: Budget)

    @Update
    fun updateBudget(budget: Budget)

    @Delete
    fun deleteBudget(budget: Budget)
}