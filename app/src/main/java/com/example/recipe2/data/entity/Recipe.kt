package com.example.recipe2.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.recipe2.data.model.Ingredient

@Entity(tableName = "recipes")
data class Recipe (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val title: String,
    @ColumnInfo(name = "category_id") val categoryId: Int,
    @ColumnInfo val imageUrl: String,
    @ColumnInfo val ingredients: List<Ingredient>,
    @ColumnInfo(name = "direction_to_cook") val directionToCook: String,
)