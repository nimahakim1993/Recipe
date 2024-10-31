package com.example.recipe2.presentation.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.recipe2.data.db.CategoryDao
import com.example.recipe2.data.db.RecipeDao
import com.example.recipe2.data.db.RecipeDatabase
import com.example.recipe2.data.util.SampleData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun provideRecipeDatabase(app: Application): RecipeDatabase {
        val databaseCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                val database = Room.databaseBuilder(
                    app,
                    RecipeDatabase::class.java,
                    "recipe_db"
                ).build()

                CoroutineScope(Dispatchers.IO).launch {
                    val categoryDao = database.getCategoryDao()
                    val recipeDao = database.getRecipeDao()

                    // Sample Categories
                    val categories = SampleData.categories
                    categories.forEach { categoryDao.insert(it) }

                    // Sample Recipes
                    val recipes = SampleData.recipes
                    recipes.forEach { recipeDao.insert(it) }
                }
            }
        }

        return Room.databaseBuilder(app, RecipeDatabase::class.java, "recipe_db")
            .addCallback(databaseCallback)
            .fallbackToDestructiveMigration()
            .build()
    }




    @Singleton
    @Provides
    fun provideCategoryDao(recipeDatabase: RecipeDatabase): CategoryDao {
        return recipeDatabase.getCategoryDao()
    }

    @Singleton
    @Provides
    fun provideRecipeDao(recipeDatabase: RecipeDatabase): RecipeDao {
        return recipeDatabase.getRecipeDao()
    }
}
