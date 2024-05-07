package com.example.lwb.core.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.lwb.core.data.entities.Day

@Dao
interface DayDao {
    @Query("SELECT * FROM Day WHERE date = :today")
    suspend fun getAll(today: String): List<Day>

    @Insert
    suspend fun insert(day: Day)

    @Delete
    suspend fun delete(vararg day: Day)
}