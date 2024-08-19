package com.finance.budgetok.contexts.sample.domain.datasource.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.finance.budgetok.contexts.sample.domain.datasource.entities.Account

@Dao
interface AccountDao {
    @Query("SELECT * FROM accounts WHERE isDeleted = 0")
    fun getAccount(): Account?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAccount(account: Account)

    @Update
    fun updateAccount(account: Account)

    @Delete
    fun deleteAccount(account: Account)
}