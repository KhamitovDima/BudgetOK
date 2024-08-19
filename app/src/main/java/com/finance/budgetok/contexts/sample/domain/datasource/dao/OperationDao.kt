package com.finance.budgetok.contexts.sample.domain.datasource.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.finance.budgetok.contexts.sample.domain.datasource.entities.Account
import com.finance.budgetok.contexts.sample.domain.datasource.entities.Operation

@Dao
interface OperationDao {
    @Query("SELECT * FROM operations WHERE isDeleted = 0")
    fun getAllOperations(): List<Operation>

    @Query("SELECT * FROM operations WHERE id = :id AND isDeleted = 0")
    fun getOperationById(id: String): Operation?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOperation(operation: Operation)

    @Update
    fun updateOperation(operation: Operation)

    @Delete
    fun deleteOperation(operation: Operation)
}