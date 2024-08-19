package com.finance.budgetok.contexts.sample.domain.datasource.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "operations")
data class Operation(
    @PrimaryKey val id: String,
    val type: Int,
    val summa: Double,
    val summa_dop: Double,
    val id_val: Int,
    val id_val_dop: Int,
    val id_account: String,
    val id_cat: String,
    val id_subcat: String?,
    val prim: String,
    val date_int: Int,
    val time_int: Int,
    val updated: String,
    val isDeleted: Int
)
