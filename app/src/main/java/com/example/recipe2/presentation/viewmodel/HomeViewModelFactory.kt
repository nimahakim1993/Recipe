package com.example.recipe2.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recipe2.repository.HomeRepository

class HomeViewModelFactory(
    private val app: Application,
    private val homeRepository: HomeRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(
                app,
                homeRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}