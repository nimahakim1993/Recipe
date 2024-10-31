package com.example.recipe2.presentation.di

import com.example.recipe2.presentation.adapter.CategoryAdapter
import com.example.recipe2.presentation.adapter.IngredientAdapter
import com.example.recipe2.presentation.adapter.RecipeAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AdapterModule {
    @Singleton
    @Provides
    fun provideCategoryAdapter(): CategoryAdapter {
        return CategoryAdapter()
    }
    @Singleton
    @Provides
    fun provideRecipeAdapter(): RecipeAdapter {
        return RecipeAdapter()
    }  @Singleton
    @Provides
    fun provideIngredientAdapter(): IngredientAdapter {
        return IngredientAdapter()
    }

}