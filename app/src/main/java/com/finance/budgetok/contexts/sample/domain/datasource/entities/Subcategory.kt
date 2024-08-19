package com.finance.budgetok.contexts.sample.domain.datasource.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subcategories")
data class Subcategory(
    @PrimaryKey val id: String,
    val id_cat: String,
    val name: String,
    val updated: String,
    val isDeleted: Int
)