package com.example.lwb.core.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.lwb.core.data.entities.Meal

@Dao
interface MealDao {
    @Query("SELECT * FROM Meal")
    fun getAll(): List<Meal>

    @Query("SELECT * from Meal WHERE date = :date")
    suspend fun getByDate(date: String): List<Meal>

    @Insert
    suspend fun insert(meal: Meal)

    @Delete
    fun delete(meal: Meal)
}