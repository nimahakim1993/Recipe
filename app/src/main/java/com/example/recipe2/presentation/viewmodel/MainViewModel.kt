package com.example.recipe2.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(
    private val app: Application,
): AndroidViewModel(app) {
    private val _label = MutableLiveData<String>()

    fun setFragmentLabel(label: String){
        _label.postValue(label)
    }
    val currentFragmentLabel :LiveData<String> get() = _label

}