package com.example.recipe2.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.recipe2.data.entity.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert()
    suspend fun insert(category: Category)

    @Query("SELECT * FROM categories")
    fun getAllCategories(): Flow<List<Category>>
}