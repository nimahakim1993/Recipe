package com.example.recipe2.presentation.di

import android.app.Application
import com.example.recipe2.presentation.viewmodel.HomeViewModelFactory
import com.example.recipe2.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class FactoryModule {

    @ActivityScoped
    @Provides
    fun provideHomeViewModelFactory(
        app: Application,
        homeRepository: HomeRepository
    ): HomeViewModelFactory{
        return HomeViewModelFactory(app, homeRepository)
    }
}