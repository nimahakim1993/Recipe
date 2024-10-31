package com.example.recipe2.data.db

import androidx.room.Dao
import androidx.room.Insert
import com.example.recipe2.data.entity.Category

@Dao
interface CategoryDao {
    @Insert()
    suspend fun insert(category: Category)

//    @Query("SELECT * FROM categories")
//    suspend fun getAllCategories(): List<Category> // Optional: method to retrieve categories
}