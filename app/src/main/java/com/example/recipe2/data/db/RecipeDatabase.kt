package com.example.recipe2.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.recipe2.data.entity.Category
import com.example.recipe2.data.entity.Recipe

@Database(
    entities = [Category::class, Recipe::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class RecipeDatabase: RoomDatabase() {
    abstract fun getCategoryDao(): CategoryDao
    abstract fun getRecipeDao(): RecipeDao
}