package com.example.lwb.core.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.lwb.core.data.entities.Meal

@Dao
interface MealDao {
    @Query("SELECT * FROM meal")
    fun getAll(): List<Meal>

    @Insert
    fun insert(meal: Meal)

    @Delete
    fun delete(meal: Meal)
}