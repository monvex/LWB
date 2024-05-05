package com.example.lwb.core.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.lwb.core.data.entities.Day

@Dao
interface DayDao {
    @Query("SELECT * FROM day")
    fun getAll(): List<Day>

    @Insert
    fun insert(day: Day)

    @Delete
    fun delete(day: Day)
}