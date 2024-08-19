package com.finance.budgetok.contexts.sample.domain.datasource.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dolgusers")
data class DolgUser(
    @PrimaryKey val id: String,
    val name: String,
    val por_nom: Int,
    val vis: Int,
    val uchet: Int,
    val updated: String,
    val isDeleted: Int
)
