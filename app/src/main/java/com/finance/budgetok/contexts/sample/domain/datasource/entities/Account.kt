package com.finance.budgetok.contexts.sample.domain.datasource.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accounts")
data class Account(
    @PrimaryKey val id: String,
    val name: String,
    val type: Int,
    val summa: Double,
    val id_val: Int,
    val id_color: Int,
    val id_icon: Int,
    val por_nom: Int,
    val vis: Int,
    val uchet: Int,
    val updated: String,
    val isDeleted: Int
)