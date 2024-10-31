package com.example.recipe2.presentation.di

import com.example.recipe2.data.db.CategoryDao
import com.example.recipe2.data.db.RecipeDao
import com.example.recipe2.repository.HomeRepository
import com.example.recipe2.repository.HomeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class RepositoryModule {

    @ActivityScoped
    @Provides
    fun provideHomeRepository(recipeDao: RecipeDao, categoryDao: CategoryDao): HomeRepository{
        return HomeRepositoryImpl(
            recipeDao,
            categoryDao
        )
    }
}