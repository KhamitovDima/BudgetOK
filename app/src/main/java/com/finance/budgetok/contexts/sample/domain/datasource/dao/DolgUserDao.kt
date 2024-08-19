package com.finance.budgetok.contexts.sample.domain.datasource.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.finance.budgetok.contexts.sample.domain.datasource.entities.Account
import com.finance.budgetok.contexts.sample.domain.datasource.entities.DolgUser

@Dao
interface DolgUserDao {
    @Query("SELECT * FROM dolgusers WHERE isDeleted = 0")
    fun getAllDolgUsers(): List<DolgUser>

    @Query("SELECT * FROM dolgusers WHERE id = :id AND isDeleted = 0")
    fun getDolgUserById(id: String): DolgUser?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDolgUser(dolgUser: DolgUser)

    @Update
    fun updateDolgUser(dolgUser: DolgUser)

    @Delete
    fun deleteDolgUser(dolgUser: DolgUser)
}