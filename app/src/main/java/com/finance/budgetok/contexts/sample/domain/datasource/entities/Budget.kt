package com.finance.budgetok.contexts.sample.domain.datasource.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "budgets")
data class Budget(
    @PrimaryKey val id: String,
    val type: Int,
    val summa: Double,
    val id_cat: String,
    val per_mode: Int,
    val per_data: Int,
    val updated: String,
    val isDeleted: Int
)
