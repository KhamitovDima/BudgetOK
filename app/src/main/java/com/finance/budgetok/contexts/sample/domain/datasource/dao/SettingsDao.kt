package com.finance.budgetok.contexts.sample.domain.datasource.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.finance.budgetok.contexts.sample.domain.datasource.entities.Account
import com.finance.budgetok.contexts.sample.domain.datasource.entities.Settings

@Dao
interface SettingsDao {
    @Query("SELECT * FROM settings")
    fun getAllSettings(): List<Settings>

    @Query("SELECT * FROM settings WHERE id = :id")
    fun getSettingById(id: Int): Settings?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSetting(setting: Settings)

    @Update
    fun updateSetting(setting: Settings)

    @Delete
    fun deleteSetting(setting: Settings)
}