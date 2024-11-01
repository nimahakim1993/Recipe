package com.example.recipe2.repository

import com.example.recipe2.data.db.CategoryDao
import com.example.recipe2.data.db.RecipeDao
import com.example.recipe2.data.entity.Category
import com.example.recipe2.data.entity.Recipe
import kotlinx.coroutines.flow.Flow

class HomeRepositoryImpl(private val recipeDao: RecipeDao, private val categoryDao: CategoryDao): HomeRepository {
    override fun getCategoryRecipes(categoryId: Int): Flow<List<Recipe>> {
        return recipeDao.getCategoryRecipes(categoryId)
    }

    override fun getAllCategories(): Flow<List<Category>> {
        return categoryDao.getAllCategories()
    }

    override fun searchRecipes(value: String): Flow<List<Recipe>> {
        return recipeDao.searchRecipes(value)
    }

    override fun searchCategories(value: String): Flow<List<Category>> {
        return categoryDao.searchCategory(value)
    }


    override suspend fun getCategoryById(categoryId: Int): Category {
        return categoryDao.getCategoryTitle(categoryId)
    }

    override suspend fun addCategory(category: Category) {
        categoryDao.insert(category)
    }

    override suspend fun addRecipe(recipe: Recipe) {
        recipeDao.insert(recipe)
    }


}