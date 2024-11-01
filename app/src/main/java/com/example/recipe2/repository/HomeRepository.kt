package com.example.recipe2.repository

import com.example.recipe2.data.entity.Category
import com.example.recipe2.data.entity.Recipe
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun getCategoryRecipes(categoryId: Int): Flow<List<Recipe>>
    fun getAllCategories(): Flow<List<Category>>

    suspend fun getCategoryById(categoryId: Int): Category
    suspend fun addCategory(category: Category)
}