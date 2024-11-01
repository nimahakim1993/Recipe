package com.example.recipe2.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.recipe2.data.entity.Category
import com.example.recipe2.data.entity.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Insert
    suspend fun insert(recipe: Recipe)
    @Query("SELECT * FROM recipes WHERE category_id = :categoryId")
    fun getCategoryRecipes(categoryId: Int): Flow<List<Recipe>>

    @Query("SELECT * FROM recipes WHERE title LIKE '%' || :value || '%'")
    fun searchRecipes(value: String): Flow<List<Recipe>>
}