package com.example.recipe2.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.recipe2.data.entity.Category
import com.example.recipe2.data.entity.Recipe
import com.example.recipe2.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class HomeViewModel(
    private val app: Application,
    private val homeRepository: HomeRepository,
): AndroidViewModel(app) {

    fun getCategoryRecipes(categoryId: Int) = homeRepository.getCategoryRecipes(categoryId).asLiveData(Dispatchers.IO)
    fun getAllCategories() = homeRepository.getAllCategories().asLiveData(Dispatchers.IO)

    fun searchRecipes(value: String) = homeRepository.searchRecipes(value).asLiveData(Dispatchers.IO)
    fun searchCategories(value: String) = homeRepository.searchCategories(value).asLiveData(Dispatchers.IO)

    fun getCategoryTitleById(categoryId: Int) = liveData {
        val category = homeRepository.getCategoryById(categoryId)
        emit(category.title)
    }

    fun addCategory(category: Category) = viewModelScope.launch {
        homeRepository.addCategory(category)
    }
    fun addRecipe(recipe: Recipe) = viewModelScope.launch {
        homeRepository.addRecipe(recipe)
    }

}