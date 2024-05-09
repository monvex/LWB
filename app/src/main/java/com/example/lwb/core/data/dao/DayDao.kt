package com.example.lwb.core.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.lwb.core.data.entities.Day
import com.example.lwb.core.data.entities.User

@Dao
interface DayDao {
    @Query("SELECT * FROM Day WHERE date = :today")
    suspend fun getByDate(today: String): List<Day>

    @Insert
    suspend fun insert(day: Day)

    @Update
    suspend fun update(vararg day: Day)
    @Delete
    suspend fun delete(vararg day: Day)
}