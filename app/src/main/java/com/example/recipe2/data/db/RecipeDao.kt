package com.example.recipe2.data.db

import androidx.room.Dao
import androidx.room.Insert
import com.example.recipe2.data.entity.Category
import com.example.recipe2.data.entity.Recipe

@Dao
interface RecipeDao {

    @Insert
    suspend fun insert(recipe: Recipe)
}