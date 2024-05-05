package com.example.lwb.core.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.lwb.core.data.entities.Exercise

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercise")
    suspend fun getAll(): List<Exercise>

    @Insert
    fun insert(exercise: Exercise)

    @Delete
    fun delete(exercise: Exercise)
}