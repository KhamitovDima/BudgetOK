package com.finance.budgetok.contexts.sample.domain.datasource.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.finance.budgetok.contexts.sample.domain.datasource.entities.Account
import com.finance.budgetok.contexts.sample.domain.datasource.entities.Subcategory

@Dao
interface SubcategoryDao {
    @Query("SELECT * FROM subcategories WHERE isDeleted = 0")
    fun getAllSubcategories(): List<Subcategory>

    @Query("SELECT * FROM subcategories WHERE id = :id AND isDeleted = 0")
    fun getSubcategoryById(id: String): Subcategory?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSubcategory(subcategory: Subcategory)

    @Update
    fun updateSubcategory(subcategory: Subcategory)

    @Delete
    fun deleteSubcategory(subcategory: Subcategory)
}