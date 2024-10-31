package com.example.recipe2.data.db

import androidx.room.TypeConverter
import com.example.recipe2.data.model.Ingredient
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromIngredientList(value: List<Ingredient>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toIngredientList(value: String): List<Ingredient>? {
        val listType = object : TypeToken<List<Ingredient>>() {}.type
        return Gson().fromJson(value, listType)
    }
}